package ca.sebon.tea_da.Database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "tea_table")
public class Tea
{
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String teaType; //{ Black, Darjeeling, Fruit, Green, Herbal, Honeybush, Jasmine, Oolong, Pu-erh, Rooibos, White, Yerba Mate  }

    @NonNull
    private int brewTimeShort;  //seconds

    @NonNull
    private int brewTimeMedium; //seconds

    @NonNull
    private int brewTimeLong;   //seconds

    @NonNull
    private String teaAmount;

    public Tea(@NonNull String teaType, @NonNull int brewTimeShort, @NonNull int brewTimeMedium, @NonNull int brewTimeLong, @NonNull String teaAmount) {
        this.teaType = teaType;
        this.brewTimeShort = brewTimeShort;
        this.brewTimeMedium = brewTimeMedium;
        this.brewTimeLong = brewTimeLong;
        this.teaAmount = teaAmount;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    @NonNull
    public int getId() {
        return id;
    }

    @NonNull
    public String getTeaType() {
        return teaType;
    }

    @NonNull
    public int getBrewTimeShort() {
        return brewTimeShort;
    }

    @NonNull
    public int getBrewTimeMedium() {
        return brewTimeMedium;
    }

    @NonNull
    public int getBrewTimeLong() {
        return brewTimeLong;
    }

    @NonNull
    public String getTeaAmount() {
        return teaAmount;
    }

    //Pre-populate DB with the entire list of Teas
    public static Tea[] populateDB()
    {
        return new Tea[]
                {
                        new Tea("Black", 180, 240, 300, "1 tsp"),
                        new Tea("Darjeeling", 120, 180, 240, "1 tsp"),
                        new Tea("Fruit", 300, 600, 900, "1-2 tsp"),
                        new Tea("Green", 120, 180, 240, "1 tsp"),
                        new Tea("Herbal", 300, 420, 600, "1 tsp"),
                        new Tea("Honeybush", 300, 360, 420, "1 tsp"),
                        new Tea("Jasmine", 120, 180, 240, "1 tsp"),
                        new Tea("Oolong", 300, 360, 420, "1 tsp"),
                        new Tea("Puerh", 300, 360, 420, "1 tbsp"),
                        new Tea("Rooibos", 300, 420, 600, "1.5 tsp"),
                        new Tea("White", 120, 180, 240, "1.5 tsp"),
                        new Tea("Yerba Mate", 300, 360, 420, "1.5 tsp")
                };
    }
}
