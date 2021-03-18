package edu.cnm.deepdive.sipandscore.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.sipandscore.model.dao.BarDao;
import edu.cnm.deepdive.sipandscore.model.entity.Bar;
import edu.cnm.deepdive.sipandscore.model.pojo.BarWithDrinkRatings;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

public class BarRepository {

  private final Context context;

  private final BarDao barDao;

  public BarRepository(Context context) {
    this.context = context;
    SipAndScoreDatabase database = SipAndScoreDatabase.getInstance();
    barDao = database.getBarDao();
  }

  public Single<Bar> save(Bar bar) {
    if (bar.getId() > 0) {
      return barDao
          .update(bar)
          .map((ignored) -> bar)
          .subscribeOn(Schedulers.io());
    } else {
      return barDao
          .insert(bar)
          .map((barId) -> {
            bar.setId(barId);
            return bar;
          })
          .subscribeOn(Schedulers.io());
    }
  }

  public Completable delete(Bar bar) {
    return (
        (bar.getId() == 0)
            ? Completable.complete()
            : barDao
                .delete(bar)
                .ignoreElement()
    )
        .subscribeOn(Schedulers.io());
  }

  public LiveData<List<Bar>> getAllByName() {
    return barDao.sortByName();
  }

  public LiveData<BarWithDrinkRatings> getById(long barId) {
    return barDao.selectById(barId);
  }
}
