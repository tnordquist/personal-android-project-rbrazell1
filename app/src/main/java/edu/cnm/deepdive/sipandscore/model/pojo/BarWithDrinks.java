package edu.cnm.deepdive.sipandscore.model.pojo;


import androidx.room.Relation;
import edu.cnm.deepdive.sipandscore.model.entity.Bar;
import edu.cnm.deepdive.sipandscore.model.entity.Drink;
import java.util.LinkedList;
import java.util.List;

public class BarWithDrinks extends Bar {

  @Relation(
      entity = Drink.class,
      entityColumn = "drink_id",
      parentColumn = "drink_id"
  )
  private List<Drink> drinkList = new LinkedList<>();

  public List<Drink> getDrinkList() {
    return drinkList;
  }

  public void setDrinkList(List<Drink> drinkList) {
    this.drinkList = drinkList;
  }
}
