package edu.cnm.deepdive.sipandscore.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import edu.cnm.deepdive.sipandscore.model.entity.Drink;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

@Dao
public interface DrinkDao {

  @Insert
  Single<Long> insert(Drink drink);

  @Insert
  Single<List<Long>> insert(Drink... drinks);

  @Insert
  Single<List<Long>> insert(Collection<Drink> drinks);


  @Update
  Single<Integer> update(Drink drink);

  @Delete
  Single<Integer> delete(Drink drink);

  @Query("SELECT * FROM Drink ORDER BY drink_name DESC")
  LiveData<String> sortByName(String name);

  @Transaction
  @Query("SELECT * FROM Drink WHERE drink_id = :drinkId")
  LiveData<Drink> selectById(long drinkId);

}
