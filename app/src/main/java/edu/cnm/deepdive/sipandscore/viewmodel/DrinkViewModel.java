package edu.cnm.deepdive.sipandscore.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import edu.cnm.deepdive.sipandscore.model.entity.Drink;
import edu.cnm.deepdive.sipandscore.service.DrinkRepository;
import io.reactivex.disposables.CompositeDisposable;
import java.util.List;

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

//  public void store(String title, String comment, String bar) {
//    throwable.postValue(null);
//    pending.add(
//        drinkRepository.save()
//
//    )
//  }

  public void saveDrink(Drink drink) {
    pending.add(
        drinkRepository
            .save(drink)
            .subscribe(
                (d) -> {
                },
                throwable::postValue
            )
    );
  }

  public LiveData<List<Drink>> loadDrink() {
    return drinkRepository.getAllByName();
  }

}
