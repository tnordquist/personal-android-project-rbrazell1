package edu.cnm.deepdive.sipandscore.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import edu.cnm.deepdive.sipandscore.databinding.FragmentDrinkListBinding;
import edu.cnm.deepdive.sipandscore.viewmodel.DrinkViewModel;

public class DrinkListFragment extends Fragment {

  private FragmentDrinkListBinding binding;
  DrinkViewModel drinkViewModel;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    binding = FragmentDrinkListBinding.inflate(inflater);
    binding.addDrink.setOnClickListener((v) -> {
      DrinkListFragmentDirections.OpenDrinkDetails action = DrinkListFragmentDirections.openDrinkDetails(0);
      Navigation.findNavController(binding.getRoot()).navigate(action);
    });
    return binding.getRoot();
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

  }
}