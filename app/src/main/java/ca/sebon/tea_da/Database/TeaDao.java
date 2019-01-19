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

    @Query("SELECT * FROM tea_table WHERE teaType IS 'Black' ORDER BY teaType ASC")
    LiveData<List<Tea>> getAllBlackTeas();

    @Query("SELECT * FROM tea_table WHERE teaType IS 'Darjeeling' ORDER BY teaType ASC")
    LiveData<List<Tea>> getAllDarjeelingTeas();

    @Query("SELECT * FROM tea_table WHERE teaType IS 'Fruit' ORDER BY teaType ASC")
    LiveData<List<Tea>> getAllFruitTeas();

    @Query("SELECT * FROM tea_table WHERE teaType IS 'Green' ORDER BY teaType ASC")
    LiveData<List<Tea>> getAllGreenTeas();

    @Query("SELECT * FROM tea_table WHERE teaType IS 'Honeybush' ORDER BY teaType ASC")
    LiveData<List<Tea>> getAllHoneybushTeas();

    @Query("SELECT * FROM tea_table WHERE teaType IS 'Jasmine' ORDER BY teaType ASC")
    LiveData<List<Tea>> getAllJasmineTeas();

    @Query("SELECT * FROM tea_table WHERE teaType IS 'Herbal' ORDER BY teaType ASC")
    LiveData<List<Tea>> getAllHerbalTeas();

    @Query("SELECT * FROM tea_table WHERE teaType IS 'Oolong' ORDER BY teaType ASC")
    LiveData<List<Tea>> getAllOolongTeas();

    @Query("SELECT * FROM tea_table WHERE teaType IS 'Pu-erh' ORDER BY teaType ASC")
    LiveData<List<Tea>> getAllPuerhTeas();

    @Query("SELECT * FROM tea_table WHERE teaType IS 'Rooibos' ORDER BY teaType ASC")
    LiveData<List<Tea>> getAllRooibosTeas();

    @Query("SELECT * FROM tea_table WHERE teaType IS 'White' ORDER BY teaType ASC")
    LiveData<List<Tea>> getAllWhiteTeas();

    @Query("SELECT * FROM tea_table WHERE teaType IS 'Yerba Mate' ORDER BY teaType ASC")
    LiveData<List<Tea>> getAllYerbaMateTeas();
}
