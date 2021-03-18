package edu.cnm.deepdive.sipandscore.viewmodel;

import android.app.Application;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import edu.cnm.deepdive.sipandscore.model.entity.Bar;
import edu.cnm.deepdive.sipandscore.model.pojo.BarWithDrinkRatings;
import edu.cnm.deepdive.sipandscore.service.BarRepository;
import io.reactivex.disposables.CompositeDisposable;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class BarViewModel extends AndroidViewModel implements LifecycleObserver {

  private final BarRepository barRepository;
  private final LiveData<BarWithDrinkRatings> bar;
  private final MutableLiveData<Long> barId;
  private final MutableLiveData<Throwable> throwable;
  private final CompositeDisposable pending;

  public BarViewModel(
      @NonNull Application application) {
    super(application);
    barRepository = new BarRepository(application);
    barId = new MutableLiveData<>();
    bar = Transformations.switchMap(barId, barRepository::getById);
    throwable = new MutableLiveData<>();
    pending = new CompositeDisposable();
  }

  public LiveData<Throwable> getThrowable() {
    return throwable;
  }

  public LiveData<List<Bar>> getBars() {
    return barRepository.getAllByName();
  }

  public LiveData<BarWithDrinkRatings> getBar() {
    return bar;
  }

  public void setBarId(long id) {
    barId.setValue(id);
  }

  public void save(Bar bar) {
    throwable.setValue(null);
    pending.add(
        barRepository
            .save(bar)
            .subscribe(
                (b) -> {/* TODO explore showing user success*/ },
                this::postThrowable
            )
    );
  }

  private void postThrowable(Throwable throwable) {
    Log.e(getClass().getName(), throwable.getMessage(), throwable);
    this.throwable.postValue(throwable);
  }
}
