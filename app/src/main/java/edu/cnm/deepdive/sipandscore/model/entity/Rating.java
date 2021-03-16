package edu.cnm.deepdive.sipandscore.model.entity;


import androidx.annotation.Size;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
    foreignKeys = {
        @ForeignKey(
            entity = Drink.class,
            parentColumns = "drink_id", childColumns = "drink_id"
        ),
        @ForeignKey(
            entity = Bar.class,
            parentColumns = "bar_id", childColumns = "bar_id"
        )
    }
)
public class Rating {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "rating_id")
  private long id;

  @Size(min = 0, max = 5)
  private int stars;

  private String comment;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
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
