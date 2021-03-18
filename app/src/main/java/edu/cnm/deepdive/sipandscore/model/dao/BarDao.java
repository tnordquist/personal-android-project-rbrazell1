package edu.cnm.deepdive.sipandscore.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import edu.cnm.deepdive.sipandscore.model.entity.Bar;
import edu.cnm.deepdive.sipandscore.model.pojo.BarWithDrinkRatings;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

@Dao
public interface BarDao {

  @Insert
  Single<Long> insert(Bar bar);

  @Insert
  Single<List<Long>> insert(Bar... bars);

  @Insert
  Single<List<Long>> insert(Collection<Bar> bars);

  @Update
  Single<Integer> update(Bar bar);

  @Delete
  Single<Integer> delete(Bar bar);

  @Query("SELECT * FROM Bar ORDER BY bar_name ASC")
  LiveData<List<Bar>> sortByName();

  @Transaction
  @Query("SELECT * FROM Bar WHERE bar_id = :barID")
  LiveData<BarWithDrinkRatings> selectById(long barID);

}

