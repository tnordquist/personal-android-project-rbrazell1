package edu.cnm.deepdive.sipandscore.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import edu.cnm.deepdive.sipandscore.model.entity.Bar;
import io.reactivex.Single;

@Dao
public interface BarDao {

  @Insert
  Single<Long> insert(Bar bar);

  @Update
  Single<Integer> update(Bar bar);

  @Delete
  Single<Integer> delete(Bar bar);

  @Query("SELECT * FROM Bar ORDER BY bar_name ASC")
  LiveData<String> sortByName(String name);

}

