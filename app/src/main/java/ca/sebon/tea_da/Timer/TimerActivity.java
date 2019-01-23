package ca.sebon.tea_da.Timer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import ca.sebon.tea_da.Database.Tea;
import ca.sebon.tea_da.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class TimerActivity extends AppCompatActivity
{
    private static final String TAG = "TimerActivity";

    //reference to the current tea object
    private Tea mCurrentTea;

    //GUI Elements
    private TextView mTextViewTeaName;
    private CircleImageView mCircleImageViewTeaImage;
    private TextView mTextViewTemperature;
    private TextView mTextViewQuantity;
    private Button mButtonSteepLight;
    private Button mButtonSteepMedium;
    private Button mButtonSteepStrong;

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

        mButtonSteepLight = findViewById(R.id.timer_button_steep_light);
        mButtonSteepLight.setText("Light");

        mButtonSteepMedium = findViewById(R.id.timer_button_steep_medium);
        mButtonSteepMedium.setText("Medium");

        mButtonSteepStrong = findViewById(R.id.timer_button_steep_strong);
        mButtonSteepStrong.setText("Strong");

    }
}
