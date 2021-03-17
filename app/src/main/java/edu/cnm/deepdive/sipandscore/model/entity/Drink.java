package edu.cnm.deepdive.sipandscore.model.entity;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Drink {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "drink_id")
  private long id;

  @NonNull
  @ColumnInfo(name = "drink_name", index = true)
  private String name;

  private String imgUrl;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getImgUrl() {
    return imgUrl;
  }

  public void setImgUrl(String imgUrl) {
    this.imgUrl = imgUrl;
  }
}
