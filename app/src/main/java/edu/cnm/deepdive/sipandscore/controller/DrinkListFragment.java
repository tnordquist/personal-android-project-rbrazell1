package edu.cnm.deepdive.sipandscore.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import edu.cnm.deepdive.sipandscore.R;
import edu.cnm.deepdive.sipandscore.adapter.DrinkAdapter;
import edu.cnm.deepdive.sipandscore.adapter.DrinkAdapter.OnDrinkListClickHelper;
import edu.cnm.deepdive.sipandscore.databinding.FragmentDrinkListBinding;
import edu.cnm.deepdive.sipandscore.viewmodel.DrinkViewModel;

public class DrinkListFragment extends Fragment implements OnDrinkListClickHelper {

  private static final int PICK_IMAGE_REQUEST = 0210;
  private FragmentDrinkListBinding binding;
  private OnDrinkListClickHelper drinkClicker;
  DrinkViewModel drinkViewModel;
  DrinkAdapter drinkAdapter;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    binding = FragmentDrinkListBinding.inflate(inflater, container, false);
    binding.drinkRecyclerView.setAdapter(drinkAdapter);
    binding.addDrink.setOnClickListener((v) -> pickImage());
    return binding.getRoot();
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    drinkViewModel = new ViewModelProvider(getActivity()).get(DrinkViewModel.class);
  }

  private void pickImage() {
    Intent intent = new Intent();
    intent.setType("image/*");
    intent.setAction(Intent.ACTION_GET_CONTENT);
    startActivityForResult(Intent.createChooser(intent,
        getString(R.string.pick_image)),
        PICK_IMAGE_REQUEST);
  }

  @Override
  public void onDrinkClick(long id, View view) {
    DrinkListFragmentDirections.OpenDrinkDetails openDrinkDetails =
        DrinkListFragmentDirections.openDrinkDetails(id);
    openDrinkDetails.setDrinkId(id);
    Navigation.findNavController(view).navigate(openDrinkDetails);
  }
}