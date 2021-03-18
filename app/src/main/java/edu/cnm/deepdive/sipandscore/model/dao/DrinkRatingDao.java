package edu.cnm.deepdive.sipandscore.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import edu.cnm.deepdive.sipandscore.model.entity.DrinkRating;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

@Dao
public interface DrinkRatingDao {

  @Insert
  Single<Long> insert(DrinkRating drinkRating);

  @Insert
  Single<List<Long>> insert(DrinkRating... drinkRatings);

  @Insert
  Single<List<Long>> insert(Collection<DrinkRating> drinkRatings);

  @Update
  Single<Integer> update(DrinkRating drinkRating);

  @Delete
  Single<Integer> delete(DrinkRating... drinkRatings);

  @Query("SELECT * FROM DrinkRating ORDER BY star_rating DESC")
  LiveData<List<DrinkRating>> sortByStars();

  @Transaction
  @Query("SELECT * FROM DrinkRating WHERE drink_rating_id = :drinkRatingId")
  LiveData<DrinkRating> selectById(long drinkRatingId);

}
