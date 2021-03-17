package edu.cnm.deepdive.sipandscore.service;

import android.content.Context;
import edu.cnm.deepdive.sipandscore.model.dao.BarDao;
import edu.cnm.deepdive.sipandscore.model.dao.DrinkDao;
import edu.cnm.deepdive.sipandscore.model.dao.RatingDao;
import edu.cnm.deepdive.sipandscore.model.entity.Bar;
import edu.cnm.deepdive.sipandscore.model.pojo.DrinkFromBars;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.Iterator;

public class DrinkRepository {

  private final Context context;

  private final DrinkDao drinkDao;

  private final RatingDao ratingDao;

  private final BarDao barDao;

  public DrinkRepository(Context context) {
    this.context = context;
    SipAndScoreDatabase database = SipAndScoreDatabase.getInstance();
    drinkDao = database.getDrinkDao();
    ratingDao = database.getRatingDao();
    barDao = database.getBarDao();
  }

  public Single<DrinkFromBars> save(DrinkFromBars drink) {
    if (drink.getId() > 0) {
      return drinkDao
          .update(drink)
          .map((ignored) -> drink)
          .subscribeOn(Schedulers.io());
    } else {
      return drinkDao
          .insert(drink)
          .flatMap((drinkId) -> {
            drink.setId(drinkId);
            for (Bar bar : drink.getBarList()) {
              bar.setId(drinkId);
            }
            return barDao.insert(drink.getBarList());
          })
          .map((barIds) -> {
            Iterable<Long> idIterator = barIds.();

          })
    }
  }

}
