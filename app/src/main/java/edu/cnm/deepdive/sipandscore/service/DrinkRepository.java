package edu.cnm.deepdive.sipandscore.service;

import android.content.Context;
import edu.cnm.deepdive.sipandscore.model.dao.BarDao;
import edu.cnm.deepdive.sipandscore.model.dao.DrinkDao;
import edu.cnm.deepdive.sipandscore.model.dao.DrinkRatingDao;
import edu.cnm.deepdive.sipandscore.model.entity.Bar;
import edu.cnm.deepdive.sipandscore.model.entity.DrinkRating;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class DrinkRepository {

  private final Context context;

  private final DrinkDao drinkDao;

  private final DrinkRatingDao drinkRatingDao;

  private final BarDao barDao;

  public DrinkRepository(Context context) {
    this.context = context;
    SipAndScoreDatabase database = SipAndScoreDatabase.getInstance();
    drinkDao = database.getDrinkDao();
    drinkRatingDao = database.getRatingDao();
    barDao = database.getBarDao();
  }

  public Single<DrinkRating> save(DrinkRating drinkRating) {
    if (drinkRating.getId() > 0) {
      return drinkDao
          .update(drinkRating)
          .map((ignored) -> drinkRating)
          .subscribeOn(Schedulers.io());
    } else {
      return drinkDao
          .insert(drinkRating)
          .flatMap((drinkId) -> {
            drinkRating.setId(drinkId);
            for (Bar bar : drinkRating.getBarList()) {
              bar.setId(drinkId);
            }
            return barDao.insert(drinkRating.getBarList());
          })
          .map((barIds) -> {
            Iterable<Long> idIterator = barIds. ();

          })
    }
  }

}
