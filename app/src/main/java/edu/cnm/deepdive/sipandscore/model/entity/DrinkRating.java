package edu.cnm.deepdive.sipandscore.model.entity;


import androidx.annotation.Size;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import java.util.Date;

@Entity(
    foreignKeys = {
        @ForeignKey(
            entity = Drink.class,
            parentColumns = "drink_id", childColumns = "drink_id",
            onDelete = ForeignKey.CASCADE
        ),
        @ForeignKey(
            entity = Bar.class,
            parentColumns = "bar_id", childColumns = "bar_id",
            onDelete = ForeignKey.CASCADE
        )
    }
)
public class DrinkRating {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "drink_rating_id")
  private long id;

  @ColumnInfo(index = true)
  private Date created = new Date();

  @ColumnInfo(name = "drink_id", index = true)
  private long drinkId;

  @ColumnInfo(name = "bar_id", index = true)
  private long barId;

  @ColumnInfo(name = "star_rating")
  @Size(min = 0, max = 5)
  private int stars;

  private String comment;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public long getDrinkId() {
    return drinkId;
  }

  public void setDrinkId(long drinkId) {
    this.drinkId = drinkId;
  }

  public long getBarId() {
    return barId;
  }

  public void setBarId(long barId) {
    this.barId = barId;
  }

  public int getStars() {
    return stars;
  }

  public void setStars(int stars) {
    this.stars = stars;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }
}
