package edu.cnm.deepdive.sipandscore.service;

import android.app.Application;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import edu.cnm.deepdive.sipandscore.model.dao.BarDao;
import edu.cnm.deepdive.sipandscore.model.dao.DrinkDao;
import edu.cnm.deepdive.sipandscore.model.dao.RatingDao;
import edu.cnm.deepdive.sipandscore.model.entity.Bar;
import edu.cnm.deepdive.sipandscore.model.entity.Drink;
import edu.cnm.deepdive.sipandscore.model.entity.Rating;

@Database(
    entities = {Bar.class, Drink.class, Rating.class},
    version = 1
)
public abstract class SipAndScoreDatabase extends RoomDatabase {

  private static final String DB_NAME = "sip_score_db";

  private static Application context;

  public static SipAndScoreDatabase getInstance() {
    return InstanceHolder.INSTANCE ;
  }

  public abstract DrinkDao getDrinkDao();

  public abstract BarDao getBarDao();

  public abstract RatingDao getRatingDao();

  private static class InstanceHolder {

  private static final SipAndScoreDatabase INSTANCE =
      Room.databaseBuilder(context, SipAndScoreDatabase.class,
          DB_NAME)
      .build();
  }


}

