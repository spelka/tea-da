package ca.sebon.tea_da.Main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.ads.MobileAds;
import com.squareup.picasso.Picasso;

import ca.sebon.tea_da.Menu.MenuActivity;
import ca.sebon.tea_da.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize the Mobile Ads SDK
        MobileAds.initialize(this, "ca-app-pub-1462446494198204~3542134273");

        ImageView imageView = findViewById(R.id.main_image_view);
        Picasso.with(this)
                .load(R.drawable.tea_da_logo)
                .fit()
                .into(imageView);
    }

    public void loadMenuActivity(View view)
    {
        Intent intent = new Intent (this, MenuActivity.class);
        startActivity(intent);
    }
}
