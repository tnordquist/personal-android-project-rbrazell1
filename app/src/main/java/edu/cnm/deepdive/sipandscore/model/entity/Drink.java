package edu.cnm.deepdive.sipandscore.model.entity;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.Date;

@Entity
public class Drink {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "drink_id")
  private long id;

  @ColumnInfo(index = true)
  private Date created = new Date();

  @NonNull
  @ColumnInfo(name = "drink_name", index = true)
  private String name;

  private String path;

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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }
}
