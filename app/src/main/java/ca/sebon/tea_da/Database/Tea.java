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
    private String teaType;

    @NonNull
    private int brewTimeShort;

    @NonNull
    private int brewTimeMedium;

    @NonNull
    private int brewTimeLong;

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
}
