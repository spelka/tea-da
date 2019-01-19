package ca.sebon.tea_da.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Tea.class}, version = 1)
public abstract class TeaDatabase extends RoomDatabase
{
    //Singleton.
    private static TeaDatabase instance;

    public abstract TeaDao teaDao();

    // Creates an instance of the TeaDatabase if one does not already exist.
    // Only one thread can access this at a time to prevent duplication.
    // Additionally, Room will generate the necessary code for our abstract function in build().
    public static synchronized TeaDatabase getInstance(Context context)
    {
        if (instance == null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    TeaDatabase.class,"tea_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }
}
