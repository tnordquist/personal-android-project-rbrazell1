package edu.cnm.deepdive.sipandscore.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import edu.cnm.deepdive.sipandscore.R;

public class DashboardViewModel extends ViewModel {

  private MutableLiveData<String> mText;

  public DashboardViewModel() {
    mText = new MutableLiveData<>();
    mText.setValue(String.valueOf(R.string.dashboard_text));
  }

  public LiveData<String> getText() {
    return mText;
  }

}
