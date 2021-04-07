package edu.cnm.deepdive.sipandscore.controller;

import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import com.squareup.picasso.Picasso;
import edu.cnm.deepdive.sipandscore.R;
import edu.cnm.deepdive.sipandscore.databinding.FragmentDrinkDetailBinding;
import org.jetbrains.annotations.NotNull;

public class DrinkDetailFragment extends DialogFragment {

  private FragmentDrinkDetailBinding binding;
  //TODO add viewmodel
  private long drinkId;
  private Uri imageUri;
  private AlertDialog dialog;


  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Bundle bundle = getArguments();
    drinkId = DrinkDetailFragmentArgs.fromBundle(bundle).getDrinkId();
    imageUri = DrinkDetailFragmentArgs.fromBundle(bundle).getImageUri();
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(
      @Nullable  Bundle savedInstanceState) {
    binding = FragmentDrinkDetailBinding.inflate(
        LayoutInflater.from(getContext()), null, false);
    dialog = new AlertDialog.Builder(getContext())
        .setTitle("Drink Details")
        .setIcon(R.drawable.ic_logo)
        .setView(binding.getRoot())
        .setNeutralButton(android.R.string.cancel, (dlg, which) -> {})
        .create();
    return dialog;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    if (imageUri != null) {
      Picasso.get().load(imageUri).into(binding.image);
    }
    return binding.getRoot();
  }
}