package ca.sebon.tea_da.Menu;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
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
import ca.sebon.tea_da.Timer.TimerActivity;

public class MenuActivity extends AppCompatActivity
{
    //reference to the viewModel
    private TeaViewModel mTeaViewModel;

    //reference to the adapter
    private MenuAdapter mAdapter;

    //reference to GUI elements
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //set up the recycler view
        mRecyclerView = findViewById(R.id.recycler_view_menu);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);

        //set up the adapter
        mAdapter = new MenuAdapter();
        mAdapter.setOnItemClickListener(new MenuAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position)
            {
                Intent intent = new Intent(getApplicationContext(), TimerActivity.class);
                intent.putExtra("id", mAdapter.getTeaList().get(position).getId());
                intent.putExtra("type", mAdapter.getTeaList().get(position).getTeaType());
                intent.putExtra("image", mAdapter.getTeaList().get(position).getTeaImage());
                intent.putExtra("amount", mAdapter.getTeaList().get(position).getTeaAmount());
                intent.putExtra("steeptimeshort", mAdapter.getTeaList().get(position).getBrewTimeShort());
                intent.putExtra("steeptimemedium", mAdapter.getTeaList().get(position).getBrewTimeMedium());
                intent.putExtra("steeptimelong", mAdapter.getTeaList().get(position).getBrewTimeLong());

                startActivity(intent);
            }
        });

        //bind the adapter to the recyclerview
        mRecyclerView.setAdapter(mAdapter);

        //We don't want use "new" here because we want to use an existing instance of the ViewModel.
        //The ViewModel reference is scoped to the lifecycle of this activity
        //When this activity is finished, the viewmodel reference will be destroyed.
        mTeaViewModel = ViewModelProviders.of(this).get(TeaViewModel.class);
        mTeaViewModel.getAllTeas().observe(this, new Observer<List<Tea>>()
        {
            @Override
            public void onChanged(@Nullable List<Tea> teaList)
            {
                //update our GUI element when a change to the DB occurs
                mAdapter.setTeaList(teaList);
            }
        });
    }
}
