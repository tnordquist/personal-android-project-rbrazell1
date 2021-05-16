package edu.cnm.deepdive.sipandscore.controller.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import edu.cnm.deepdive.sipandscore.R;
import edu.cnm.deepdive.sipandscore.adapter.DrinkAdapter;
import edu.cnm.deepdive.sipandscore.adapter.DrinkAdapter.OnDrinkListClickHelper;
import edu.cnm.deepdive.sipandscore.controller.fragment.DrinkListFragmentDirections.OpenDrinkDetails;
import edu.cnm.deepdive.sipandscore.databinding.FragmentChoosePictureBinding;
import edu.cnm.deepdive.sipandscore.databinding.FragmentDrinkListBinding;
import edu.cnm.deepdive.sipandscore.model.entity.Drink;
import edu.cnm.deepdive.sipandscore.service.DrinkRepository;
import edu.cnm.deepdive.sipandscore.service.ImageRepository;
import edu.cnm.deepdive.sipandscore.viewmodel.DrinkViewModel;

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
    drinkViewModel.loadDrink()
                  .observe(getViewLifecycleOwner(), (drinkList) -> {
                    if (drinkList != null) {
                      drinkList.forEach((drink) -> {
                        String path = drink.getPath();
                        Log.d(getClass().getName(), path);
                        if (path != null && !path.isEmpty()) {
                          drink.setPath(imageRepository.resolvePath(path));
                          Log.d(getClass().getName(), drink.getPath());
                        }
                      });
                      binding.drinkRecyclerView.setAdapter(
                          new DrinkAdapter(getContext(), this, drinkList));
                    }
                  });
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
      OpenDrinkDetails openDrinkDetails =
          DrinkListFragmentDirections.openDrinkDetails();
      openDrinkDetails.setImageUri(data.getData());
      Navigation.findNavController(binding.getRoot())
                .navigate(openDrinkDetails);

    }
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    FragmentChoosePictureBinding pictureBinding =
        FragmentChoosePictureBinding.inflate(LayoutInflater.from(getContext()));

    return new AlertDialog.Builder(getContext())
        .setTitle("Add a new picture")
        .setView(pictureBinding.getRoot())
        .setNeutralButton(R.string.close, (dlg, which) -> {
//          Left empty to close dialog
        })
        .create();

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
    Navigation.findNavController(view)
              .navigate(openDrinkDetails);
  }
}