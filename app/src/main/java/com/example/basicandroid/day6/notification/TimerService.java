package com.example.basicandroid.day6.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.IBinder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.JobIntentService;
import androidx.core.app.NotificationCompat;

public class TimerService extends Service {

    public static final String BROADCAST_TIME_TIK_ACTION
            = "com.example.basicandroid.day6.notification.TimerService.BROADCAST_TIME_TIK_ACTION";
    public static final String BROADCAST_TIME_TIK_DATA
            = "com.example.basicandroid.day6.notification.TimerService.BROADCAST_TIME_TIK_DATA";

    private Intent intentTimeTik;
    private static final String myChannelId = "myChannelId";
    private static final String myChannel = "myChannel";
    private NotificationManager notificationManager;
    private static final int notificationCreditCardId = 1212;
    private static final int requestOK = 1010;
    private static final int requestNO = 0101;

    private CountDownTimer countDownTimer;

    @Override
    public void onCreate() {
        super.onCreate();
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        intentTimeTik = new Intent(BROADCAST_TIME_TIK_ACTION);
        countDownTimer = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long time = millisUntilFinished / 1000;
                intentTimeTik.putExtra(BROADCAST_TIME_TIK_DATA, time);
                sendBroadcast(intentTimeTik);
            }
            @Override
            public void onFinish() {
                setupNotification();
                stopSelf();
            }
        };
        countDownTimer.start();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void setupNotification(){
        NotificationCompat.Builder builder = createNotification()
                .addAction(android.R.drawable.ic_menu_save, "Pay Now", addActionOK())
                .addAction(android.R.drawable.ic_delete, "Pay Later", addActionNO());
        addNotificationToNotificationManager(builder);
    }

    /**
     * channel notification
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createChannelNotification(){
        NotificationChannel channel = new NotificationChannel(myChannelId, myChannel, NotificationManager.IMPORTANCE_DEFAULT);
        notificationManager.createNotificationChannel(channel);
    }

    /**
     * create notification
     */
    private NotificationCompat.Builder createNotification(){
        NotificationCompat.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createChannelNotification();
            builder = new NotificationCompat.Builder(this, myChannelId)
                    .setSmallIcon(android.R.drawable.ic_notification_overlay)
                    .setContentTitle("Credit Card")
                    .setContentText("Mohon segera membayar tagihan kartu kredit Anda!");
        } else {
            builder = new NotificationCompat.Builder(this)
                    .setSmallIcon(android.R.drawable.ic_notification_overlay)
                    .setContentTitle("Credit Card")
                    .setContentText("Mohon segera membayar tagihan kartu kredit Anda!");
        }
        return builder;
    }

    /**
     * add notification to Notification Manager
     *
     */
    private void addNotificationToNotificationManager(NotificationCompat.Builder builder){
        notificationManager.notify(notificationCreditCardId, builder.build());
    }

    /**
     * add action OK to notification
     */
    private PendingIntent addActionOK(){
        Intent intent = new Intent(this, Day6PaymentCreditCardActivity.class);
        int flag = PendingIntent.FLAG_CANCEL_CURRENT;
        return PendingIntent.getActivity(this, requestOK, intent, flag);
    }

    /**
     * add action NO to notification
     */
    private PendingIntent addActionNO(){
        Intent intent = new Intent(this, Day6PaymentCreditCardActivity.class);
        int flag = PendingIntent.FLAG_CANCEL_CURRENT;
        return PendingIntent.getActivity(this, requestNO, intent, flag);
    }

    /**
     * simpel notification
     */
    private void createSimpleNotification(){
        // init notification
        NotificationCompat.Builder notification;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            String myChannelId = "myChannelId";
            final String myChannel = "myChannel";
            NotificationChannel channel = new NotificationChannel(myChannelId, myChannel
                    , NotificationManager.IMPORTANCE_DEFAULT);
            notification = new NotificationCompat.Builder(this, myChannelId)
                    .setSmallIcon(android.R.drawable.ic_notification_overlay)
                    .setContentTitle("Credit Card")
                    .setContentText("Mohon segera membayar tagihan kartu kredit Anda!");
        }
        else{
            notification = new NotificationCompat.Builder(this, myChannelId)
                    .setSmallIcon(android.R.drawable.ic_notification_overlay)
                    .setContentTitle("Credit Card")
                    .setContentText("Mohon segera membayar tagihan kartu kredit Anda!");
        }

        // buat dan tambahkan aksi
        Intent intent = new Intent(this, Day6PaymentCreditCardActivity.class);
        int flag = PendingIntent.FLAG_CANCEL_CURRENT;
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1010, intent, flag);

        notification.addAction(android.R.drawable.ic_menu_save, "Pay Now", pendingIntent);

        // tambahkan notifikasi ke manager notifikasi
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1111, notification.build());
    }
}
