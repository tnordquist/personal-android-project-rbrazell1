package edu.cnm.deepdive.sipandscore.controller;

import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


public class CameraFragment extends DialogFragment implements TextWatcher {

  // TODO ask if this is the right place to bind to the camera fragment or the Drink rating card fragment. or the other way around
  private FragmentCameraBinding binding;
  private AlertDialog dialog;
  private DrinkViewModel drinkViewModel;
  private List<Drink> drinkList;
  private Uri uri;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    binding = FragmentCameraBinding.inflate(
        LayoutInflater.from(getContext()), null, false);
    dialog = new Builder(getContext())
        .setIcon(R.drawable.ic_add)
        .setTitle(R.string.add_drink_dialog_title)
        .setView(binding.getRoot())
        .setNeutralButton(android.R.string.cancel, (dlg, which) -> {
          /* This shouldn't do anything*/
        })
        .setPositiveButton(android.R.string.ok, (dlg, which) -> addDrinkToList())
        .create();
    dialog.setOnShowListener((dlg) -> {
      binding.fragmentCamera.getViewById(R.id.fragment_camera);
    });
    return dialog;

  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return binding.getRoot();
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
  }
}



