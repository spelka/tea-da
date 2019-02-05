package ca.sebon.tea_da.Timer;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import ca.sebon.tea_da.R;

import static ca.sebon.tea_da.BaseApplication.NOTIFICATION_CHANNEL_ID;

public class AlertReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);

        Notification notification = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notifications_active_black_24dp)
                .setContentTitle("Tea-Da!")
                .setContentText("Your tea is ready! Enjoy!")
                .setPriority(NotificationManagerCompat.IMPORTANCE_DEFAULT)
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .build();

        notificationManagerCompat.notify(1, notification);
    }
}
