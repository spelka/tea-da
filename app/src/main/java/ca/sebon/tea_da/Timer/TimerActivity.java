package ca.sebon.tea_da.Timer;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.support.v4.app.NotificationCompat;
import android.widget.Toast;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.Timer;

import ca.sebon.tea_da.Database.Tea;
import ca.sebon.tea_da.Main.MainActivity;
import ca.sebon.tea_da.R;
import de.hdodenhof.circleimageview.CircleImageView;

import static ca.sebon.tea_da.BaseApplication.NOTIFICATION_CHANNEL_ID;

public class TimerActivity extends AppCompatActivity
{

    //reference to the current tea object
    private Tea mCurrentTea;

    //reference to the timer object
    private CountDownTimer mCountDownTimer;
    private long mRemainingTimeMilliseconds= 0;
    private boolean mTimerRunning;
    int mCountDownIntervalMilliSeconds = 1000;

    Date mTimerPausedTime;
    Date mTimerResumedTime;
    long mDeltaTime;

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
    private TextView mTextViewInfoMessage;

    private LinearLayout mLinearLayoutConfiguration;
    private LinearLayout mLinearLayoutInformation;

    //Notifications
    private NotificationManagerCompat notificationManagerCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
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
                        intent.getStringExtra("temperature"),
                        intent.getIntExtra("image", 0)
                );


        //Customize the View for the activity
        buildTimerActivity();

        //Set up the notification manager
        notificationManagerCompat = NotificationManagerCompat.from(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        mTimerPausedTime = new Date();
        startAlarm();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTimerResumedTime = new Date();
        stopAlarm();
    }

    public void buildTimerActivity()
    {
        mTextViewTeaName = findViewById(R.id.timer_text_view_tea_name);
        mTextViewTeaName.setText(mCurrentTea.getTeaType());

        mCircleImageViewTeaImage = findViewById(R.id.timer_circle_image_view_tea_image);
        Picasso.with(this)
                .load(mCurrentTea.getTeaImage())
                .fit()
                .into(mCircleImageViewTeaImage);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        //dynamically resize for smaller devices to prevent clipping of UI elements
        if ( size.x <= 720 ) {
            mCircleImageViewTeaImage.getLayoutParams().width = size.x / 3;
            mCircleImageViewTeaImage.getLayoutParams().height = size.x / 3;
        }
        else
        {
            mCircleImageViewTeaImage.getLayoutParams().width = size.x / 2;
            mCircleImageViewTeaImage.getLayoutParams().height = size.x / 2;
        }

        mTextViewTemperature = findViewById(R.id.timer_text_view_temperature);
        mTextViewTemperature.setText(mCurrentTea.getSteepTemperature());

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
                mRemainingTimeMilliseconds = mCurrentTea.getSteepTimeShort();
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
                mRemainingTimeMilliseconds = mCurrentTea.getSteepTimeMedium();
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
                mRemainingTimeMilliseconds = mCurrentTea.getSteepTimeLong();
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

        mLinearLayoutConfiguration = findViewById(R.id.timer_linear_layout_panel_configuration);
        mLinearLayoutConfiguration.setVisibility(View.VISIBLE);

        mLinearLayoutInformation = findViewById(R.id.timer_linear_layout_panel_information);
        mLinearLayoutInformation.setVisibility(View.GONE);

        mTextViewInfoMessage = findViewById(R.id.timer_text_view_info_message);

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
        if (mRemainingTimeMilliseconds > 0)
        {
            mCountDownTimer = new CountDownTimer(mRemainingTimeMilliseconds, mCountDownIntervalMilliSeconds) {

                @Override
                public void onTick(long millisUntilFinished) {
                    mRemainingTimeMilliseconds = millisUntilFinished;
                    updateTimer();
                }

                @Override
                public void onFinish() {
                    sendNotification();
                    mTextViewInfoMessage.setText("Tea-Da! Your tea is ready! Enjoy!");
                    mRemainingTimeMilliseconds = 0;
                    updateTimer();
                    mButtonStartStop.setText("Steep Another Tea");
                    mButtonStartStop.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v)
                        {
                            //load the countdown activity
                            Intent intent = new Intent (getBaseContext(), MainActivity.class);
                            startActivity(intent);
                        }
                    });
                }
            }.start();

            mButtonStartStop.setText("Stop");
            setInfoDisplay();
            mTimerRunning = true;
        }
    }

    public void stopTimer()
    {
        mCountDownTimer.cancel();
        mButtonStartStop.setText("Start");
        setConfigDisplay();
        mRemainingTimeMilliseconds = 0;
        updateTimer();
        mTimerRunning = false;
    }

    public void setConfigDisplay()
    {
        mLinearLayoutConfiguration.setVisibility(View.VISIBLE);
        mLinearLayoutInformation.setVisibility(View.GONE);
    }

    public void setInfoDisplay()
    {
        mLinearLayoutConfiguration.setVisibility(View.GONE);
        mLinearLayoutInformation.setVisibility(View.VISIBLE);
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

    public void sendNotification()
    {
        Notification notification = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notifications_active_black_24dp)
                .setContentTitle("Tea-Da!")
                .setContentText("Your tea is ready! Enjoy!")
                .setPriority(NotificationManagerCompat.IMPORTANCE_HIGH)
                .build();

        notificationManagerCompat.notify(1, notification);
    }

    //Invoke AlarmManager when the phone is locked and in doze-standby mode
    //https://developer.android.com/training/monitoring-device-state/doze-standby
    private void startAlarm()
    {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, mRemainingTimeMilliseconds, pendingIntent);
    }

    private void stopAlarm()
    {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        alarmManager.cancel(pendingIntent);
    }

}
