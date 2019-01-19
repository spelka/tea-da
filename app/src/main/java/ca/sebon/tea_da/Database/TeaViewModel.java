package ca.sebon.tea_da.Database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class TeaViewModel extends AndroidViewModel
{
    private TeaRepository repository;
    private LiveData<List<Tea>> allTeas;

    public TeaViewModel(@NonNull Application application)
    {
        super(application);
        repository = new TeaRepository(application);
        allTeas = repository.getAllTeas();
    }

    //Wrapper methods for our DB operations

    public void insert(Tea tea)
    {
        repository.insert(tea);
    }

    public void update(Tea tea)
    {
        repository.update(tea);
    }

    public void delete(Tea tea)
    {
        repository.delete(tea);
    }

    public void deleteAllTeas()
    {
        repository.deleteAllTeas();
    }

    public LiveData<List<Tea>> getAllTeas()
    {
        return allTeas;
    }

}
