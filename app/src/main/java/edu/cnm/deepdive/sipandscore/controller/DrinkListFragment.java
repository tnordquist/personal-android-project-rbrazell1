package edu.cnm.deepdive.sipandscore.controller;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import edu.cnm.deepdive.sipandscore.R;
import edu.cnm.deepdive.sipandscore.adapter.DrinkAdapter;
import edu.cnm.deepdive.sipandscore.adapter.DrinkAdapter.OnDrinkListClickHelper;
import edu.cnm.deepdive.sipandscore.databinding.FragmentDrinkListBinding;
import edu.cnm.deepdive.sipandscore.service.ImageRepository;
import edu.cnm.deepdive.sipandscore.viewmodel.DrinkViewModel;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.jetbrains.annotations.NotNull;

public class DrinkListFragment extends DialogFragment implements OnDrinkListClickHelper {

  private static final int PICK_IMAGE_REQUEST = 0210;
  private FragmentDrinkListBinding binding;
  private OnDrinkListClickHelper drinkClicker;
  private ImageRepository imageRepository;
  private DrinkViewModel drinkViewModel;
  private DrinkAdapter drinkAdapter;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater,
      ViewGroup container,
      Bundle savedInstanceState) {
    binding = FragmentDrinkListBinding.inflate(inflater, container, false);
    binding.addDrink.setOnClickListener((v) -> pickImage());
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    drinkViewModel = new ViewModelProvider(getActivity()).get(DrinkViewModel.class);
    imageRepository = new ImageRepository(getContext());
    drinkViewModel.loadDrink().observe(getViewLifecycleOwner(), (drinkList) -> {
      if (drinkList != null) {
        drinkList.forEach((drink) -> {
          String path = drink.getPath();
          Log.d(getClass().getName(), path);
          if (path != null && !path.isEmpty()) {
            drink.setPath(imageRepository.resolvePath(path));
            Log.d(getClass().getName(), drink.getPath());
          }
        });
        binding.drinkRecyclerView.setAdapter(new DrinkAdapter(getContext(), this, drinkList));
      }
    });
  }


  @Override
  public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
      DrinkListFragmentDirections.OpenDrinkDetails openDrinkDetails =
          DrinkListFragmentDirections.openDrinkDetails();
      openDrinkDetails.setImageUri(data.getData());
      Navigation.findNavController(binding.getRoot()).navigate(openDrinkDetails);

    }
  }

  @NonNull
  @NotNull
  @Override
  public Dialog onCreateDialog(
      @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
    return super.onCreateDialog(savedInstanceState);
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
//    TODO set The properties
    DrinkListFragmentDirections.OpenDrinkDetails openDrinkDetails =
        DrinkListFragmentDirections.openDrinkDetails();
    openDrinkDetails.setDrinkId(id);
    Navigation.findNavController(view).navigate(openDrinkDetails);
  }
}