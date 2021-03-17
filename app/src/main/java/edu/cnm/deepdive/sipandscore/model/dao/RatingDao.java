package edu.cnm.deepdive.sipandscore.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import edu.cnm.deepdive.sipandscore.model.entity.Rating;
import io.reactivex.Single;

@Dao
public interface RatingDao {

  @Insert
  Single<Long> insert(Rating rating);

  @Update
  Single<Integer> update(Rating rating);

  @Delete
  Single<Integer> delete(Rating rating);

  @Query("SELECT * FROM Rating ORDER BY star_rating DESC")
  LiveData<Integer> sortByStars(int stars);

}
