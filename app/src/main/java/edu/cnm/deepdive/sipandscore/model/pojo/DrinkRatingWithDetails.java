package edu.cnm.deepdive.sipandscore.model.pojo;

import androidx.room.Relation;
import edu.cnm.deepdive.sipandscore.model.entity.Bar;
import edu.cnm.deepdive.sipandscore.model.entity.Drink;
import edu.cnm.deepdive.sipandscore.model.entity.DrinkRating;

public class DrinkRatingWithDetails extends DrinkRating {

  @Relation(
      entity = Bar.class,
      parentColumn = "bar_id",
      entityColumn = "bar_id"
  )
  private Bar bar;

  @Relation(
      entity = Drink.class,
      parentColumn = "drink_id",
      entityColumn = "drink_id"
  )
  private Drink drink;

  public Bar getBar() {
    return bar;
  }

  public void setBar(Bar bar) {
    this.bar = bar;
  }

  public Drink getDrink() {
    return drink;
  }

  public void setDrink(Drink drink) {
    this.drink = drink;
  }
}
