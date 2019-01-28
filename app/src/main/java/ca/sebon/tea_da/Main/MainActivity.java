package ca.sebon.tea_da.Main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;

import ca.sebon.tea_da.Menu.MenuActivity;
import ca.sebon.tea_da.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = findViewById(R.id.main_image_view);
        Picasso.with(this)
                .load(R.drawable.tea_da_logo)
                .fit()
                .into(imageView);
    }

    public void loadMenuActivity(View view)
    {
        Log.d(TAG, "loadMenuActivity called.");
        Intent intent = new Intent (this, MenuActivity.class);
        startActivity(intent);
    }
}
