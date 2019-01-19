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

    @Query("SELECT * FROM tea_table WHERE teaType IS 'black' ORDER BY teaName ASC")
    LiveData<List<Tea>> getAllBlackTeas();

    @Query("SELECT * FROM tea_table WHERE teaType IS 'green' ORDER BY teaName ASC")
    LiveData<List<Tea>> getAllGreenTeas();

    @Query("SELECT * FROM tea_table WHERE teaType IS 'white' ORDER BY teaName ASC")
    LiveData<List<Tea>> getAllWhiteTeas();

    @Query("SELECT * FROM tea_table WHERE teaType IS 'herbal' ORDER BY teaName ASC")
    LiveData<List<Tea>> getAllHerbalTeas();

    @Query("SELECT * FROM tea_table WHERE teaType IS 'rooibos' ORDER BY teaName ASC")
    LiveData<List<Tea>> getAllRooibosTeas();
}
