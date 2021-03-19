package edu.cnm.deepdive.sipandscore.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.sipandscore.R;
import edu.cnm.deepdive.sipandscore.databinding.FragmentBarBinding;
import edu.cnm.deepdive.sipandscore.model.entity.Bar;
import edu.cnm.deepdive.sipandscore.viewmodel.BarViewModel;

public class BarFragment extends Fragment {

  private FragmentBarBinding binding;
  private BarViewModel barViewModel;

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState
  ) {
    binding = FragmentBarBinding.inflate(inflater, container, false);
    binding.addBar.setOnClickListener((v) -> {
      Bar bar = new Bar();
      bar.setName(binding.barName.getText().toString().trim());
      barViewModel.save(bar);
    });
    return binding.getRoot();
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    barViewModel = new ViewModelProvider(getActivity()).get(BarViewModel.class);
    barViewModel.getBars().observe(getViewLifecycleOwner(), (bars) -> {
      ArrayAdapter<Bar> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, bars);
      binding.barList.setAdapter(adapter);
    });
  }
}