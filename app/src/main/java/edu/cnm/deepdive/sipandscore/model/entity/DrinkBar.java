package edu.cnm.deepdive.sipandscore.model.entity;


import androidx.room.Entity;
import androidx.room.ForeignKey;

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
    public class DrinkBar {



}
