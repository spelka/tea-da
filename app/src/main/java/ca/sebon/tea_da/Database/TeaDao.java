package ca.sebon.tea_da.Database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

//This class contains operations we want to do on our Tea Entity
public interface TeaDao
{
    @Insert
    void insert (Tea tea);

    @Update
    void update (Tea tea);

    @Delete
    void delete(Tea tea);

    @Query("DELETE FROM tea_table")
    void deleteAllTeas();

    @Query("SELECT * FROM tea_table")
    LiveData<List<Tea>> getAllTeas();
}
