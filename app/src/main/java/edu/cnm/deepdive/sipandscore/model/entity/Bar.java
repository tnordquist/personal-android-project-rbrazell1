package edu.cnm.deepdive.sipandscore.model.entity;


import androidx.annotation.NonNull;
import androidx.annotation.Size;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.Date;
import org.jetbrains.annotations.NotNull;

@Entity
public class Bar {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "bar_id")
  private long id;

  @ColumnInfo(index = true)
  private Date created = new Date();

  @NonNull
  @ColumnInfo(name = "bar_name")
  private String name;

  private String address;

  private String phoneNumber;

  private String comment;

  private String url;

  @ColumnInfo(name = "star_rating")
  @Size(min = 0, max = 5)
  private int stars;

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

  @NonNull
  public String getName() {
    return name;
  }

  public void setName(@NonNull String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public int getStars() {
    return stars;
  }

  public void setStars(int stars) {
    this.stars = stars;
  }

  @NonNull
  @Override
  public String toString() {
    return name;
  }
}
