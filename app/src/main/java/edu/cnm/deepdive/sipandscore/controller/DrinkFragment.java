package edu.cnm.deepdive.sipandscore.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import edu.cnm.deepdive.sipandscore.R;
import edu.cnm.deepdive.sipandscore.databinding.FragmentDrinkBinding;
import edu.cnm.deepdive.sipandscore.viewmodel.DrinkViewModel;

public class DrinkFragment extends Fragment {

  private FragmentDrinkBinding binding;
  DrinkViewModel drinkViewModel;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    binding = FragmentDrinkBinding.inflate(inflater);
    return binding.getRoot();
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
//        NavHostFragment.findNavController(DrinkFragment.this)
//            .navigate(R.id.action_FirstFragment_to_SecondFragment);
      }
    });
  }
}