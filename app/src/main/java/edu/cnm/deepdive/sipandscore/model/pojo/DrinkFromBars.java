package edu.cnm.deepdive.sipandscore.model.pojo;

import androidx.annotation.NonNull;
import androidx.room.Relation;
import edu.cnm.deepdive.sipandscore.model.entity.Bar;
import edu.cnm.deepdive.sipandscore.model.entity.Drink;
import java.util.LinkedList;
import java.util.List;

public class DrinkFromBars extends Drink {

  @Relation(
      entity = Bar.class,
      parentColumn = "bar_id",
      entityColumn = "bar_id"
  )
  private List<Bar> barList = new LinkedList<>();

  @NonNull
  public List<Bar> getBarList() {
    return barList;
  }

  public void setBarList(List<Bar> barList) {
    this.barList = barList;
  }
}
