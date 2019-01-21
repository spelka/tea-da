package ca.sebon.tea_da.Database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import ca.sebon.tea_da.R;

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

    @NonNull
    private int teaImage;

    public Tea(@NonNull String teaType, @NonNull int brewTimeShort, @NonNull int brewTimeMedium, @NonNull int brewTimeLong, @NonNull String teaAmount, @NonNull int teaImage) {
        this.teaType = teaType;
        this.brewTimeShort = brewTimeShort;
        this.brewTimeMedium = brewTimeMedium;
        this.brewTimeLong = brewTimeLong;
        this.teaAmount = teaAmount;
        this.teaImage = teaImage;
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

    @NonNull
    public int getTeaImage() {
        return teaImage;
    }

    //Pre-populate DB with the entire list of Teas
    public static Tea[] populateDB()
    {
        return new Tea[]
                {
                        new Tea("Black", 180, 240, 300, "1 tsp", R.drawable.black_tea),
                        new Tea("Darjeeling", 120, 180, 240, "1 tsp", R.drawable.darjeeling_tea),
                        new Tea("Fruit", 300, 600, 900, "1-2 tsp", R.drawable.fruit_tea),
                        new Tea("Green", 120, 180, 240, "1 tsp", R.drawable.green_tea),
                        new Tea("Herbal", 300, 420, 600, "1 tsp", R.drawable.herbal_tea),
                        new Tea("Honeybush", 300, 360, 420, "1 tsp", R.drawable.honeybush_tea),
                        new Tea("Jasmine", 120, 180, 240, "1 tsp", R.drawable.jasmine_tea),
                        new Tea("Oolong", 300, 360, 420, "1 tsp", R.drawable.oolong_tea),
                        new Tea("Puerh", 300, 360, 420, "1 tbsp", R.drawable.puerh_tea),
                        new Tea("Rooibos", 300, 420, 600, "1.5 tsp", R.drawable.rooibos_tea),
                        new Tea("White", 120, 180, 240, "1.5 tsp", R.drawable.white_tea),
                        new Tea("Yerba Mate", 300, 360, 420, "1.5 tsp", R.drawable.yerbamate_tea)
                };
    }
}
