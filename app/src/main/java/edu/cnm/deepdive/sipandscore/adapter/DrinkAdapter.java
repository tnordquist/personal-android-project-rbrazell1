package edu.cnm.deepdive.sipandscore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import edu.cnm.deepdive.sipandscore.adapter.DrinkAdapter.Holder;
import edu.cnm.deepdive.sipandscore.databinding.ItemDrinkDetailBinding;
import edu.cnm.deepdive.sipandscore.model.entity.Drink;
import edu.cnm.deepdive.sipandscore.model.entity.DrinkRating;
import java.util.List;

public class DrinkAdapter extends RecyclerView.Adapter<Holder> {

  private final Context context;
  private final LayoutInflater inflater;
  private final OnDrinkListClickHelper drinkClicker;
  private final List<Drink> drinkList;

  public DrinkAdapter(
      Context context, OnDrinkListClickHelper drinkClicker, List<Drink> drinkList) {
    this.context = context;
    inflater = LayoutInflater.from(context);
    this.drinkClicker = drinkClicker;
    this.drinkList = drinkList;
  }

  public List<Drink> getDrinkList() {
    return drinkList;
  }

  @NonNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    ItemDrinkDetailBinding binding = ItemDrinkDetailBinding.inflate(
        inflater, parent, false);
    return new Holder(binding, drinkClicker);
  }

  @Override
  public void onBindViewHolder(
      @NonNull Holder holder, int position) {
    holder.bind(drinkList.get(position));
  }

  @Override
  public int getItemCount() {
    return drinkList.size();
  }

  public interface OnDrinkListClickHelper {

    void onDrinkClick(long id, View view);
  }

  class Holder extends RecyclerView.ViewHolder implements OnClickListener {

    private final ItemDrinkDetailBinding binding;
    OnDrinkListClickHelper drinkClicker;
    private Drink drink;
    private DrinkRating drinkRating;

    public Holder(ItemDrinkDetailBinding binding, OnDrinkListClickHelper drinkClicker) {
      super(binding.getRoot());
      this.binding = binding;
      this.drinkClicker = drinkClicker;
      binding.getRoot().setOnClickListener(this);
    }

    private void bind(Drink drink) {
      Picasso.get()
          .load(drink.getPath())
          .into(binding.drinkThumbnailImage);
//      int stars = drinkRating.getStars();
      String name = drink.getName();
      binding.drinkThumbnailTitle.setText(name);
//      binding.drinkThumbnailRating.setRating(stars);
      binding.getRoot().setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
      drinkClicker.onDrinkClick(drinkList.get(getAdapterPosition()).getId(), view);
    }
  }
}
