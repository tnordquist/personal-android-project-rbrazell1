package edu.cnm.deepdive.sipandscore.service;

import android.content.Context;
import edu.cnm.deepdive.sipandscore.model.dao.BarDao;
import edu.cnm.deepdive.sipandscore.model.dao.DrinkDao;
import edu.cnm.deepdive.sipandscore.model.dao.RatingDao;

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
}
