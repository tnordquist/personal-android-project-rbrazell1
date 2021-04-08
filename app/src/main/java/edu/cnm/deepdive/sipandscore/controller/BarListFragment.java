package edu.cnm.deepdive.sipandscore.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.sipandscore.databinding.FragmentBarListBinding;
import edu.cnm.deepdive.sipandscore.model.entity.Bar;
import edu.cnm.deepdive.sipandscore.viewmodel.BarViewModel;

public class BarListFragment extends Fragment {

  private FragmentBarListBinding binding;
  private BarViewModel barViewModel;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater,
      ViewGroup container,
      Bundle savedInstanceState) {
    binding = FragmentBarListBinding.inflate(inflater, container, false);
    binding.barName.setThreshold(1);
    binding.search.setOnClickListener((v) -> {
      barViewModel.setNameFragment(binding.barName.getText().toString().trim());
    });
    return binding.getRoot();
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    barViewModel = new ViewModelProvider(getActivity()).get(BarViewModel.class);
    barViewModel.getBars().observe(getViewLifecycleOwner(), (bars) -> {
      ArrayAdapter<Bar> adapter = new ArrayAdapter<>(getContext(),
          android.R.layout.simple_list_item_1, bars);
      binding.barName.setAdapter(adapter);
    });
    barViewModel.getFilteredBars().observe(getViewLifecycleOwner(), (bars) -> {
      ArrayAdapter<Bar> adapter = new ArrayAdapter<>(getContext(),
          android.R.layout.simple_list_item_1, bars);
      binding.barList.setAdapter(adapter);
    });
  }
}