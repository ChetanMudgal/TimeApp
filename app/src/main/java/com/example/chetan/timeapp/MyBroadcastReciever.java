package com.example.chetan.timeapp;

        import android.app.Notification;
        import android.app.NotificationManager;
        import android.app.PendingIntent;
        import android.content.BroadcastReceiver;
        import android.content.Context;
        import android.content.Intent;
        import android.os.Vibrator;
        import android.support.v4.app.NotificationCompat;
        import android.util.Log;
        import android.widget.Toast;

        import java.util.Calendar;
        import java.util.Locale;

/**
 * Created by SACHIN on 10/9/2017.
 */

public class MyBroadcastReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent arg1) {
        showNotification(context);
    }

    private void showNotification(Context context) {
        Log.i("notification", "visible");
        Calendar sCalendar = Calendar.getInstance();
        String dayLongName = sCalendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
        PendingIntent contentIntent;
        switch (dayLongName)
        {
            case "Monday":contentIntent = PendingIntent.getActivity(context, 0,
                    new Intent(context, Monday.class), 0);

                break;
            case "Tuesday":
                contentIntent = PendingIntent.getActivity(context, 0,
                        new Intent(context, Tuesday.class), 0);
                break;
            case "Wednesday":contentIntent = PendingIntent.getActivity(context, 0,
                    new Intent(context, Wednsday.class), 0);
                break;
            case "Thursday":contentIntent = PendingIntent.getActivity(context, 0,
                    new Intent(context, Thursday.class), 0);
                break;
            case "Friday":contentIntent = PendingIntent.getActivity(context, 0,
                    new Intent(context, Friday.class), 0);
                break;
            case "Saturday":contentIntent = PendingIntent.getActivity(context, 0,
                    new Intent(context, Saturday.class), 0);
                break;
            case "Sunday":contentIntent = PendingIntent.getActivity(context, 0,
                    new Intent(context, Sunday.class), 0);
                break;
            default:contentIntent = PendingIntent.getActivity(context, 0,
                    new Intent(context, Monday.class), 0);
        }


        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.splash)
                        .setContentTitle("TIMEAPP")
                        .setContentText("There is a class at this time");
        mBuilder.setContentIntent(contentIntent);
        mBuilder.setDefaults(Notification.DEFAULT_SOUND);
        mBuilder.setAutoCancel(true);
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, mBuilder.build());
    }
}