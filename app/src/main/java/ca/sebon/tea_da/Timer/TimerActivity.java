package ca.sebon.tea_da.Timer;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;

import ca.sebon.tea_da.Database.Tea;
import ca.sebon.tea_da.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class TimerActivity extends AppCompatActivity
{
    private static final String TAG = "TimerActivity";

    //reference to the current tea object
    private Tea mCurrentTea;

    //reference to the timer object
    private CountDownTimer mCountDownTimer;
    private long mRemainingTimeMilliseconds = 600000;
    private boolean mTimerRunning;
    int mCountDownIntervalMilliSeconds = 1000;

    //GUI Elements
    private TextView mTextViewTeaName;
    private CircleImageView mCircleImageViewTeaImage;
    private TextView mTextViewTemperature;
    private TextView mTextViewQuantity;
    private Button mButtonSteepLight;
    private Button mButtonSteepMedium;
    private Button mButtonSteepStrong;
    private TextView mTextViewCountDown;
    private Button mButtonStartStop;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.d(TAG, "onCreate: started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        Intent intent = getIntent();
        mCurrentTea = new Tea
                (
                        intent.getStringExtra("type"),
                        intent.getIntExtra("steeptimeshort", 0),
                        intent.getIntExtra("steeptimemedium", 0),
                        intent.getIntExtra("steeptimelong", 0),
                        intent.getStringExtra("amount"),
                        intent.getIntExtra("image", 0)
                );


        //Customize the View for the activity
        buildTimerActivity();

        Log.d(TAG, "onCreate: finished");
    }

    public void buildTimerActivity()
    {
        mTextViewTeaName = findViewById(R.id.timer_text_view_tea_name);
        mTextViewTeaName.setText(mCurrentTea.getTeaType());

        mCircleImageViewTeaImage = findViewById(R.id.timer_circle_image_view_tea_image);
        mCircleImageViewTeaImage.setImageResource(mCurrentTea.getTeaImage());

        mTextViewTemperature = findViewById(R.id.timer_text_view_temperature);
        mTextViewTemperature.setText(mCurrentTea.getTeaAmount());

        mTextViewQuantity = findViewById(R.id.timer_text_view_quantity);
        mTextViewQuantity.setText(mCurrentTea.getTeaAmount());

        mTextViewCountDown = findViewById(R.id.timer_text_view_countdown);
        mTextViewCountDown.setText("0:00");

        mButtonSteepLight = findViewById(R.id.timer_button_steep_light);
        mButtonSteepLight.setText("Light");
        mButtonSteepLight.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mRemainingTimeMilliseconds = mCurrentTea.getBrewTimeShort();
                updateTimer();
            }
        });

        mButtonSteepMedium = findViewById(R.id.timer_button_steep_medium);
        mButtonSteepMedium.setText("Medium");
        mButtonSteepMedium.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mRemainingTimeMilliseconds = mCurrentTea.getBrewTimeMedium();
                updateTimer();
            }
        });

        mButtonSteepStrong = findViewById(R.id.timer_button_steep_strong);
        mButtonSteepStrong.setText("Strong");
        mButtonSteepStrong.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mRemainingTimeMilliseconds = mCurrentTea.getBrewTimeLong();
                updateTimer();
            }
        });

        mButtonStartStop = findViewById(R.id.timer_button_start);
        mButtonStartStop.setText("Start");
        mButtonStartStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //load the countdown activity
                toggleTimer();
            }
        });

    }

    public void toggleTimer()
    {
        if (mTimerRunning)
        {
            stopTimer();
        }
        else
        {
            startTimer();
        }
    }

    public void startTimer()
    {
        mCountDownTimer = new CountDownTimer(mRemainingTimeMilliseconds, mCountDownIntervalMilliSeconds) {

            @Override
            public void onTick(long millisUntilFinished)
            {
                mRemainingTimeMilliseconds = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {

            }
        }.start();

        mButtonStartStop.setText("Stop");
        mTimerRunning = true;
    }

    public void stopTimer()
    {
        mCountDownTimer.cancel();
        mButtonStartStop.setText("Start");
        mTimerRunning = false;
    }


    public void updateTimer()
    {
        int remainingMinutes = (int) mRemainingTimeMilliseconds / 60000;
        int remainingSeconds = (int) mRemainingTimeMilliseconds % 60000 / 1000;

        String timeLeftText;

        timeLeftText = "" + remainingMinutes + ":";
        if (remainingSeconds < 10) timeLeftText += "0";
        timeLeftText += remainingSeconds;

        mTextViewCountDown.setText(timeLeftText);
    }
}
