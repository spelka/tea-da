package ca.sebon.tea_da.Database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

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
                    .addCallback(roomCallback)
                    .build();
        }

        return instance;
    }

    //Callback function for pre-populating the database
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback()
    {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db)
        {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    //Asynchronous function for inserting all teas into the database via the populateDB function
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>
    {
        private TeaDao teaDao;

        private PopulateDbAsyncTask(TeaDatabase db)
        {
            teaDao = db.teaDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            teaDao.insertAll(Tea.populateDB());
            return null;
        }
    }
}
