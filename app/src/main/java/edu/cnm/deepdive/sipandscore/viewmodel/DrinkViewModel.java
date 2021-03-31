package edu.cnm.deepdive.sipandscore.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;
import org.jetbrains.annotations.NotNull;

public class DrinkViewModel extends AndroidViewModel{

  public DrinkViewModel drinkViewModel;

  public DrinkViewModel(@NonNull Application application) {
    super(application);
  }
}
