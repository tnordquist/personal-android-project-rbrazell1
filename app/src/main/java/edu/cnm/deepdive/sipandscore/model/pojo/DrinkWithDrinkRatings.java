package edu.cnm.deepdive.sipandscore.model.pojo;

import androidx.room.Relation;
import edu.cnm.deepdive.sipandscore.model.entity.Drink;
import edu.cnm.deepdive.sipandscore.model.entity.DrinkRating;
import java.util.List;

public class DrinkWithDrinkRatings extends Drink {

  @Relation(
      entity = DrinkRating.class,
      parentColumn = "drink_id",
      entityColumn = "drink_id"
  )
  private List<DrinkRatingWithDetails> drinkRatings;

  public List<DrinkRatingWithDetails> getDrinkRatings() {
    return drinkRatings;
  }

  public void setDrinkRatings(
      List<DrinkRatingWithDetails> drinkRatings) {
    this.drinkRatings = drinkRatings;
  }
}
