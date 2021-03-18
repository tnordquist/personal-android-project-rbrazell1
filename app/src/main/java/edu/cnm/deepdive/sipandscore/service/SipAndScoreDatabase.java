package edu.cnm.deepdive.sipandscore.service;

import android.app.Application;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import edu.cnm.deepdive.sipandscore.model.dao.BarDao;
import edu.cnm.deepdive.sipandscore.model.dao.DrinkDao;
import edu.cnm.deepdive.sipandscore.model.dao.DrinkRatingDao;
import edu.cnm.deepdive.sipandscore.model.entity.Bar;
import edu.cnm.deepdive.sipandscore.model.entity.Drink;
import edu.cnm.deepdive.sipandscore.model.entity.DrinkRating;
import edu.cnm.deepdive.sipandscore.service.SipAndScoreDatabase.Converters;
import java.util.Date;

@Database(
    entities = {Bar.class, Drink.class, DrinkRating.class},
    version = 1
)
@TypeConverters(Converters.class)
public abstract class SipAndScoreDatabase extends RoomDatabase {

  private static final String DB_NAME = "sip_score_db";

  private static Application context;

  public static SipAndScoreDatabase getInstance() {
    return InstanceHolder.INSTANCE;
  }

  public abstract DrinkDao getDrinkDao();

  public abstract BarDao getBarDao();

  public abstract DrinkRatingDao getRatingDao();

  public static void setContext(Application context) {
    SipAndScoreDatabase.context = context;
  }

  private static class InstanceHolder {

    private static final SipAndScoreDatabase INSTANCE =
        Room.databaseBuilder(context, SipAndScoreDatabase.class,
            DB_NAME)
            .build();
  }

  public static class Converters {
    @TypeConverter
    public static Long dateToLong(Date value) {
      return (value != null) ? value.getTime() : null;
    }

    @TypeConverter
    public static Date longToDate(Long value) {
      return (value != null) ? new Date(value) : null;
    }
  }
}

