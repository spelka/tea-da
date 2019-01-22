package ca.sebon.tea_da.Timer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Chronometer;

import ca.sebon.tea_da.Database.Tea;
import ca.sebon.tea_da.R;

public class TimerActivity extends AppCompatActivity
{
    private static final String TAG = "TimerActivity";
    
    private Tea currentTea;
    private Chronometer chronometer;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.d(TAG, "onCreate: started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        Log.d(TAG, "onCreate: finished");
    }
}
