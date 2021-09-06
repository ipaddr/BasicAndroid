package com.example.basicandroid.day6.notification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.basicandroid.R;

public class Day6NotificationAlarmFragment extends Fragment {

    public static final String ALARM_ACTION
            = "com.example.basicandroid.day6.notification.Day6NotificationAlarmFragment.ALARM_ACTION";
    private AlarmManager alarmMgr;


    private PendingIntent alarmIntent;
    private final int PENDING_INTENT_CODE = 1111;

    public View rootView;
    public Button alarmButton;

    private BroadcastReceiver alarmBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equalsIgnoreCase(ALARM_ACTION))
                Toast.makeText(requireActivity(), "Toas trigger by alarm via Pending Intent", Toast.LENGTH_SHORT).show();
        }
    };

    public static Day6NotificationAlarmFragment newInstance(){ return new Day6NotificationAlarmFragment();}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.day6_notification_alarm_fragment, container, false);
        alarmButton = rootView.findViewById(R.id.fire_alarm);
        alarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fireAlarm();
            }
        });
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        requireActivity().registerReceiver(alarmBroadcastReceiver, new IntentFilter(ALARM_ACTION));
    }

    @Override
    public void onStop() {
        requireActivity().unregisterReceiver(alarmBroadcastReceiver);
        super.onStop();
    }

    private void fireAlarm(){
        AlarmManager alarmMgr = (AlarmManager)requireActivity().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(ALARM_ACTION);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(requireActivity(), 0, intent, 0);
        // reepeat every 1 minutes
        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000 * 60 * 1, alarmIntent);
    }
}
