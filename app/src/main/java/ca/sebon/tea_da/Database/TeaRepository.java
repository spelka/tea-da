package ca.sebon.tea_da.Database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class TeaRepository
{
    private TeaDao teaDao;
    private LiveData<List<Tea>> allTeas;

    //Parameter application: the context in which we will create our DB instance
    //The purpose of this class is to expose repository functions to the rest of the program.
    //The ViewModel will interface with the database through this abstraction layer.
    public TeaRepository(Application application)
    {
        TeaDatabase database = TeaDatabase.getInstance(application);
        teaDao = database.teaDao();
        allTeas = teaDao.getAllTeas();
    }

    public void insert(Tea tea)
    {
        new InsertTeaAsyncTask(teaDao).execute(tea);
    }

    public void update(Tea tea)
    {
        new UpdateTeaAsyncTask(teaDao).execute(tea);
    }

    public void delete(Tea tea)
    {
        new DeleteTeaAsyncTask(teaDao).execute(tea);
    }

    public void deleteAllTeas()
    {
        new DeleteAllTeasAsyncTask(teaDao).execute();
    }

    public LiveData<List<Tea>> getAllTeas()
    {
        return allTeas;
    }

    //We must define Asynchronous tasks as inner classes in order to execute DB operations on the background thread
    //Static inner class prevents the inner class having a reference to the repository, otherwise could cause a memory leak

    //Asynchronous Insert Operation
    private static class InsertTeaAsyncTask extends AsyncTask<Tea, Void, Void>
    {
        private TeaDao teaDao;

        //static class cannot access outer Dao, pass it in via constructor
        private InsertTeaAsyncTask(TeaDao teaDao)
        {
            this.teaDao = teaDao;
        }

        @Override
        protected Void doInBackground(Tea... teas) {
            //varargs passed to async task, we will only pass one element at a time, so insert the first argument only.
            teaDao.insert(teas[0]);
            return null;
        }
    }

    //Asynchronous Update Operation
    private static class UpdateTeaAsyncTask extends AsyncTask<Tea, Void, Void>
    {
        private TeaDao teaDao;

        //static class cannot access outer Dao, pass it in via constructor
        private UpdateTeaAsyncTask(TeaDao teaDao)
        {
            this.teaDao = teaDao;
        }

        @Override
        protected Void doInBackground(Tea... teas) {
            //varargs passed to async task, we will only pass one element at a time, so insert the first argument only.
            teaDao.update(teas[0]);
            return null;
        }
    }

    //Asynchronous Delete Operation
    private static class DeleteTeaAsyncTask extends AsyncTask<Tea, Void, Void>
    {
        private TeaDao teaDao;

        //static class cannot access outer Dao, pass it in via constructor
        private DeleteTeaAsyncTask(TeaDao teaDao)
        {
            this.teaDao = teaDao;
        }

        @Override
        protected Void doInBackground(Tea... teas) {
            //varargs passed to async task, we will only pass one element at a time, so insert the first argument only.
            teaDao.delete(teas[0]);
            return null;
        }
    }

    //Asynchronous Delete All Operation
    private static class DeleteAllTeasAsyncTask extends AsyncTask<Void, Void, Void>
    {
        private TeaDao teaDao;

        //static class cannot access outer Dao, pass it in via constructor
        private DeleteAllTeasAsyncTask(TeaDao teaDao)
        {
            this.teaDao = teaDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //varargs passed to async task, we will only pass one element at a time, so insert the first argument only.
            teaDao.deleteAllTeas();
            return null;
        }
    }
}
