package ca.sebon.tea_da;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class BaseApplication extends Application
{
    public static final String NOTIFICATION_CHANNEL_ID = "Tea-Da Notification Channel";

    @Override
    public void onCreate()
    {
        super.onCreate();

        createNotificationChannel();
    }

    private void createNotificationChannel()
    {
        //Only need to do this for Android Oreo or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel notificationChannel = new NotificationChannel(
                    NOTIFICATION_CHANNEL_ID,
                    "Tea-Da Notifications",
                    NotificationManager.IMPORTANCE_HIGH
            );
            notificationChannel.setDescription("Tea-Da! Your tea is ready!");

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
}
