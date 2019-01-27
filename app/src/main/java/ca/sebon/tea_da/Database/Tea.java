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
    private int steepTimeShort;  //milliseconds

    @NonNull
    private int steepTimeMedium; //milliseconds

    @NonNull
    private int steepTimeLong;   //milliseconds

    @NonNull
    private String teaAmount;

    @NonNull
    private String steepTemperature;


    @NonNull
    private int teaImage;

    public Tea(@NonNull String teaType, @NonNull int steepTimeShort, @NonNull int steepTimeMedium, @NonNull int steepTimeLong, @NonNull String teaAmount, @NonNull String steepTemperature, @NonNull int teaImage) {
        this.teaType = teaType;
        this.steepTimeShort = steepTimeShort;
        this.steepTimeMedium = steepTimeMedium;
        this.steepTimeLong = steepTimeLong;
        this.teaAmount = teaAmount;
        this.steepTemperature = steepTemperature;
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
    public int getSteepTimeShort() {
        return steepTimeShort;
    }

    @NonNull
    public int getSteepTimeMedium() {
        return steepTimeMedium;
    }

    @NonNull
    public int getSteepTimeLong() {
        return steepTimeLong;
    }

    @NonNull
    public String getSteepTemperature() {
        return steepTemperature;
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
                        new Tea("Black", 180000, 240000, 300000, "1 tsp", "100"+"\u00b0"+"C / 200"+"\u00b0"+"F", R.drawable.black_tea),
                        new Tea("Darjeeling", 120000, 180000, 240000, "1 tsp", "85"+"\u00b0"+"C / 185"+"\u00b0"+"F", R.drawable.darjeeling_tea),
                        new Tea("Fruit", 300000, 600000, 900000, "1-2 tsp", "100"+"\u00b0"+"C / 200"+"\u00b0"+"F", R.drawable.fruit_tea),
                        new Tea("Green", 120000, 180000, 240000, "1 tsp", "85"+"\u00b0"+"C / 185"+"\u00b0"+"F", R.drawable.green_tea),
                        new Tea("Herbal", 300000, 420000, 600000, "1 tsp", "100"+"\u00b0"+"C / 200"+"\u00b0"+"F", R.drawable.herbal_tea),
                        new Tea("Honeybush", 300000, 360000, 420000, "1 tsp", "100"+"\u00b0"+"C / 200"+"\u00b0"+"F", R.drawable.honeybush_tea),
                        new Tea("Jasmine", 120000, 180000, 240000, "1 tsp", "80"+"\u00b0"+"C / 175"+"\u00b0"+"F", R.drawable.jasmine_tea),
                        new Tea("Oolong", 300000, 360000, 420000, "1 tsp", "88"+"\u00b0"+"C / 190"+"\u00b0"+"F", R.drawable.oolong_tea),
                        new Tea("Puerh", 300000, 360000, 420000, "1 tbsp", "88"+"\u00b0"+"C / 190"+"\u00b0"+"F", R.drawable.puerh_tea),
                        new Tea("Rooibos", 300000, 420000, 600000, "1.5 tsp", "100"+"\u00b0"+"C / 200"+"\u00b0"+"F", R.drawable.rooibos_tea),
                        new Tea("White", 120000, 180000, 240000, "1.5 tsp", "80"+"\u00b0"+"C / 175"+"\u00b0"+"F", R.drawable.white_tea),
                        new Tea("Yerba Mate", 300000, 360000, 420000, "1.5 tsp", "75"+"\u00b0"+"C / 170"+"\u00b0"+"F", R.drawable.yerbamate_tea)
                };
    }
}
