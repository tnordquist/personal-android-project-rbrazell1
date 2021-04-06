package edu.cnm.deepdive.sipandscore.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import edu.cnm.deepdive.sipandscore.model.entity.Drink;
import edu.cnm.deepdive.sipandscore.service.DrinkRepository;
import io.reactivex.disposables.CompositeDisposable;
import java.util.Date;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class DrinkViewModel extends AndroidViewModel implements LifecycleObserver {
  
  private final DrinkRepository drinkRepository;
  private final MutableLiveData<Drink> drink;
  private final MutableLiveData<List<Drink>> drinkList;
  private final CompositeDisposable pending;
  private final MutableLiveData<Throwable> throwable;
  

  public DrinkViewModel drinkViewModel;

  public DrinkViewModel(@NonNull Application application) {
    super(application);
    drinkRepository = new DrinkRepository(application);
    drink = new MutableLiveData<>();
    drinkList = new MutableLiveData<>();
    pending = new CompositeDisposable();
    throwable = new MutableLiveData<>();
  }

  public LiveData<Drink> getDrink() {
    return drink;
  }

  public LiveData<List<Drink>> getDrinkList() {
    return drinkList;
  }

  public LiveData<Throwable> getThrowable() {
    return throwable;
  }

  public void store(long id, String name) {
    throwable.postValue(null);
  }
}
