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
    private String teaName;

    @NonNull
    private String teaType;

    @NonNull
    private int brewTimeShort;

    @NonNull
    private int brewTimeMedium;

    @NonNull
    private int brewTimeLong;

    @NonNull
    private String measureAmount;

    public Tea(String teaName, String teaType, int brewTimeShort, int brewTimeMedium, int brewTimeLong, String measureAmount) {
        this.teaName = teaName;
        this.teaType = teaType;
        this.brewTimeShort = brewTimeShort;
        this.brewTimeMedium = brewTimeMedium;
        this.brewTimeLong = brewTimeLong;
        this.measureAmount = measureAmount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTeaName() {
        return teaName;
    }

    public String getTeaType() {
        return teaType;
    }

    public int getBrewTimeShort() {
        return brewTimeShort;
    }

    public int getBrewTimeMedium() {
        return brewTimeMedium;
    }

    public int getBrewTimeLong() {
        return brewTimeLong;
    }

    public String getMeasureAmount() {
        return measureAmount;
    }
}
