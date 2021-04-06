package edu.cnm.deepdive.sipandscore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.sipandscore.adapter.DrinkAdapter.Holder;
import edu.cnm.deepdive.sipandscore.databinding.FragmentDrinkDetailBinding;
import edu.cnm.deepdive.sipandscore.model.entity.Drink;
import edu.cnm.deepdive.sipandscore.model.entity.DrinkRating;
import java.util.List;
import org.jetbrains.annotations.NotNull;

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

  @NonNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    FragmentDrinkDetailBinding binding = FragmentDrinkDetailBinding.inflate(
        inflater, parent, false);
    return new Holder(binding, drinkClicker);
  }

  @Override
  public void onBindViewHolder(
      @NonNull Holder holder, int position) {
   holder.bind(position);
  }

  @Override
  public int getItemCount() {
    return drinkList.size();
  }

  class Holder extends RecyclerView.ViewHolder implements OnClickListener {

    private final FragmentDrinkDetailBinding binding;
    OnDrinkListClickHelper drinkClicker;
    private Drink drink;
    private DrinkRating drinkRating;

    public Holder(FragmentDrinkDetailBinding binding, OnDrinkListClickHelper drinkClicker) {
      super(binding.getRoot());
      this.binding = binding;
      this.drinkClicker = drinkClicker;
      binding.getRoot().setOnClickListener((OnClickListener) this);
    }

    private void bind(int position) {
      drink = drinkList.get(position);
      binding.drinkThumbnailTitle.setText(drink.getName()); //TODO How do I link the rating to the drink?
      binding.drinkThumbnailRating.setRating(drinkRating.getStars());
      binding.getRoot().setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
      drinkClicker.onDrinkClick(drinkList.get(getAdapterPosition()).getId(), view);
    }
  }

  public interface OnDrinkListClickHelper {
    void onDrinkClick(long id, View view);
  }
}
