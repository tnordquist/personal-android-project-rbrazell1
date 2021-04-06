package edu.cnm.deepdive.sipandscore.controller;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.fragment.app.DialogFragment;
import edu.cnm.deepdive.sipandscore.R;
import edu.cnm.deepdive.sipandscore.databinding.FragmentCameraBinding;
import edu.cnm.deepdive.sipandscore.model.entity.Drink;
import edu.cnm.deepdive.sipandscore.viewmodel.DrinkViewModel;
import java.util.List;
import org.jetbrains.annotations.NotNull;


public class CameraFragment extends DialogFragment implements TextWatcher {
// TODO ask if this is the right place to bind to the camera fragment or the Drink rating card fragment. or the other way around
  private FragmentCameraBinding binding;

  private AlertDialog dialog;
  private DrinkViewModel drinkViewModel;
  private List<Drink> drinkList;

  @NonNull
  @NotNull
  @Override
  public Dialog onCreateDialog(@Nullable  Bundle savedInstanceState) {
    binding = FragmentCameraBinding.inflate(
        LayoutInflater.from(getContext()), null, false);
    dialog = new Builder(getContext())
        .setIcon(R.drawable.ic_add)
        .setTitle(R.string.add_drink_dialog_title)
        .setView(binding.getRoot())
        .setNeutralButton(android.R.string.cancel, (dlg, which) -> {
          /* This shouldn't do anything*/
        })
        .setPositiveButton(android.R.string.ok, (dlg, which) -> )
    return dialog;

  }

  @Override
  public void beforeTextChanged(CharSequence s, int start, int count, int after) {

  }

  @Override
  public void onTextChanged(CharSequence s, int start, int before, int count) {

  }

  @Override
  public void afterTextChanged(Editable s) {

  }

  private void addDrinkToList() {
    String name = binding.
}