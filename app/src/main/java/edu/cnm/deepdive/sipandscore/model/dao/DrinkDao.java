package edu.cnm.deepdive.sipandscore.model.dao;

import static android.icu.text.MessagePattern.ArgType.SELECT;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import edu.cnm.deepdive.sipandscore.model.entity.Drink;
import io.reactivex.Single;
import io.reactivex.internal.operators.flowable.FlowableWithLatestFromMany;

@Dao
public interface DrinkDao {

  @Insert
  Single<Long> insert(Drink drink);

  @Update
  Single<Integer> update(Drink drink);

  @Delete
  Single<Integer> delete(Drink drink);

  @Query("SELECT * FROM Drink ORDER BY drink_name DESC")
  LiveData<String> sortByName(String name);
}
