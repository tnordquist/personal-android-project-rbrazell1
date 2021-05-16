package edu.cnm.deepdive.sipandscore.controller.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.sipandscore.R;
import edu.cnm.deepdive.sipandscore.controller.BarDetailFragmentArgs;
import edu.cnm.deepdive.sipandscore.databinding.FragmentBarDetailBinding;
import edu.cnm.deepdive.sipandscore.model.entity.Bar;
import edu.cnm.deepdive.sipandscore.viewmodel.BarViewModel;

public class BarDetailFragment extends DialogFragment implements TextWatcher {

  private FragmentBarDetailBinding binding;
  private BarViewModel barViewModel;
  private AlertDialog dialog;


  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Bundle bundle = getArguments();
    long barId = BarDetailFragmentArgs.fromBundle(bundle).getBarId();
    barViewModel = new ViewModelProvider(getActivity()).get(BarViewModel.class);
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(
      @Nullable Bundle savedInstanceState) {
    binding = FragmentBarDetailBinding.inflate(
        LayoutInflater.from(getContext()), null, false);
    dialog = new Builder(getContext())
        .setTitle(R.string.bar_dialog_title)
        .setIcon(R.drawable.ic_logo)
        .setView(binding.getRoot())
        .setNeutralButton(android.R.string.cancel, (dlg, which) -> {
        })
        .setPositiveButton(android.R.string.ok, (dlg, which) -> saveBarToList())
        .create();
    dialog.setOnShowListener((dlg) -> {
      binding.barName.addTextChangedListener(this);
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
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    binding.barName.addTextChangedListener(this);
    binding.phoneNumber.addTextChangedListener(this);
  }

  private void saveBarToList() {
    Bar bar = new Bar();
    String name = binding.barName.getText().toString().trim();
    String comment = binding.commentDescription.getText().toString().trim();
    String number = binding.phoneNumber.getText().toString().trim();
    String address = binding.addAddress.getText().toString().trim();
    int rating = (int) binding.starRating.getRating();
    bar.setName(name);
    bar.setComment(comment);
    bar.setPhoneNumber(number);
    bar.setAddress(address);
    bar.setStars(rating);
    barViewModel.save(bar);
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
    positive.setEnabled(!binding.barName.getText().toString().trim().isEmpty());
  }

  private void barBind(Bar bar) {
    String name = bar.getName();
    String comment = bar.getComment();
    String address = bar.getAddress();
    String number = bar.getPhoneNumber();

  }

}