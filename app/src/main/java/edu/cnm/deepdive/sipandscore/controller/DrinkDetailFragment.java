package edu.cnm.deepdive.sipandscore.controller;

import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import com.squareup.picasso.Picasso;
import edu.cnm.deepdive.sipandscore.R;
import edu.cnm.deepdive.sipandscore.databinding.FragmentDrinkDetailBinding;
import edu.cnm.deepdive.sipandscore.model.entity.Drink;
import edu.cnm.deepdive.sipandscore.viewmodel.DrinkViewModel;

public class DrinkDetailFragment extends DialogFragment implements TextWatcher {

  private FragmentDrinkDetailBinding binding;
  private DrinkViewModel drinkViewModel;
  private long drinkId;
  private Uri imageUri;
  private AlertDialog dialog;


  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Bundle bundle = getArguments();
    drinkId = DrinkDetailFragmentArgs.fromBundle(bundle).getDrinkId();
    imageUri = DrinkDetailFragmentArgs.fromBundle(bundle).getImageUri();
    drinkViewModel = new ViewModelProvider(getActivity()).get(DrinkViewModel.class);
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(
      @Nullable Bundle savedInstanceState) {
    binding = FragmentDrinkDetailBinding.inflate(
        LayoutInflater.from(getContext()), null, false);
    dialog = new AlertDialog.Builder(getContext())
        .setTitle("Drink Details")
        .setIcon(R.drawable.ic_logo)
        .setView(binding.getRoot())
        .setNeutralButton(android.R.string.cancel, (dlg, which) -> {
        })
        .setPositiveButton(android.R.string.ok, (dlg, which) -> saveDrinkToList())
        .create();
    dialog.setOnShowListener((dlg) -> {
      binding.imageTitle.addTextChangedListener(this);
      checkSubmitConditions();
    });
    return dialog;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view,
      @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    if (imageUri != null) {
      Picasso.get()
          .load(imageUri)
          .into(binding.image);
    }
    binding.imageTitle.addTextChangedListener(this);
    if (getArguments() != null) {
      DrinkDetailFragmentArgs args = DrinkDetailFragmentArgs.fromBundle(getArguments());
      imageUri = args.getImageUri();

    }
  }

  //  private void addDrinkToList() {
//    String title = binding.imageTitle.getText().toString().trim();
//    String comment = binding.commentDescription.getText().toString().trim();
//    String bar = binding.addBar.getText().toString().trim();
//    drinkViewModel.saveDrink();
//  }

  private void saveDrinkToList() {
    Drink drink = new Drink();
    String title = binding.imageTitle.getText().toString().trim();
    drink.setName(title);
    drink.setPath(imageUri.getPath());
    drinkViewModel.saveDrink(drink, imageUri);
  }

  @Override
  public void beforeTextChanged(CharSequence s, int start, int count, int after) {

  }

  @Override
  public void onTextChanged(CharSequence s, int start, int before, int count) {

  }

  @Override
  public void afterTextChanged(Editable s) {
    checkSubmitConditions();
  }

  public void checkSubmitConditions() {
    Button positive = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
    positive.setEnabled(!binding.imageTitle.getText().toString().trim().isEmpty());
  }
}