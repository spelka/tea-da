package ca.sebon.tea_da.Menu;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import ca.sebon.tea_da.Database.Tea;
import ca.sebon.tea_da.Database.TeaViewModel;
import ca.sebon.tea_da.R;

public class MenuActivity extends AppCompatActivity
{
    private TeaViewModel teaViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //Create a reference to our recyclerview
        RecyclerView recyclerView = findViewById(R.id.recycler_view_menu);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final MenuAdapter adapter = new MenuAdapter();
        recyclerView.setAdapter(adapter);

        //We don't want use "new" here because we want to use an existing instance of the ViewModel.
        //The ViewModel reference is scoped to the lifecycle of this activity
        //When this activity is finished, the viewmodel reference will be destroyed.
        teaViewModel = ViewModelProviders.of(this).get(TeaViewModel.class);
        teaViewModel.getAllTeas().observe(this, new Observer<List<Tea>>()
        {
            @Override
            public void onChanged(@Nullable List<Tea> teaList)
            {
                //update our GUI element when a change to the DB occurs
                adapter.setTeaList(teaList);
            }
        });
    }
}
