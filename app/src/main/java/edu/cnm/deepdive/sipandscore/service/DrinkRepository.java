package edu.cnm.deepdive.sipandscore.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.sipandscore.model.dao.DrinkDao;
import edu.cnm.deepdive.sipandscore.model.dao.DrinkRatingDao;
import edu.cnm.deepdive.sipandscore.model.entity.Drink;
import edu.cnm.deepdive.sipandscore.model.entity.DrinkRating;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

public class DrinkRepository {

  private final Context context;

  private final DrinkDao drinkDao;

  private final DrinkRatingDao drinkRatingDao;


  public DrinkRepository(Context context) {
    this.context = context;
    SipAndScoreDatabase database = SipAndScoreDatabase.getInstance();
    drinkDao = database.getDrinkDao();
    drinkRatingDao = database.getRatingDao();
  }

// DRINKRATING methods

  public Single<DrinkRating> save(DrinkRating drinkRating) {
    if (drinkRating.getId() > 0) {
      return drinkRatingDao
          .update(drinkRating)
          .map((ignored) -> drinkRating)
          .subscribeOn(Schedulers.io());
    } else {
      return drinkRatingDao
          .insert(drinkRating)
          .map((drinkRatingId) -> {
            drinkRating.setId(drinkRatingId);
            return drinkRating;
          });
    }
  }

  public Completable delete(DrinkRating drinkRating) {
    return (
        (drinkRating.getId() == 0)
            ? Completable.complete()
            : drinkRatingDao
                .delete(drinkRating)
                .ignoreElement()
    )
        .subscribeOn(Schedulers.io());
  }

  public LiveData<List<DrinkRating>> getAllByStar() {
    return drinkRatingDao.sortByStars();
  }

  public LiveData<DrinkRating> getDrinkRating(long drinkRatingId) {
    return drinkRatingDao.selectById(drinkRatingId);
  }

// DRINK methods

  public Single<Drink> save(Drink drink) {
    if (drink.getId() > 0) {
      return drinkDao
          .update(drink)
          .map((ignored) -> drink)
          .subscribeOn(Schedulers.io());
    } else {
      return drinkDao
          .insert(drink)
          .map((drinkId) -> {
            drink.setId(drinkId);
            return drink;
          })
          .subscribeOn(Schedulers.io());
    }
  }

  public Completable delete(Drink drink) {
    return (
        (drink.getId() == 0)
            ? Completable.complete()
            : drinkDao
                .delete(drink)
                .ignoreElement()
    )
        .subscribeOn(Schedulers.io());
  }

  public LiveData<List<Drink>> getAllByName() {
    return drinkDao.sortByName();
  }

  public LiveData<Drink> getDrinkId(long drinkId) {
    return drinkDao.selectById(drinkId);
  }

}
