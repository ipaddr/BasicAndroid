package com.example.basicandroid.day6.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.basicandroid.R;

public class Day6NotificationFragment extends Fragment {

    private View rootView;
    private TextView counterTimeView;

    private BroadcastReceiver timeTikBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equalsIgnoreCase(TimerService.BROADCAST_TIME_TIK_ACTION)){
                long time = intent.getLongExtra(TimerService.BROADCAST_TIME_TIK_DATA,0);
                if (isAdded()){
                    counterTimeView.setText(String.valueOf(time));
                }
            }
        }
    };

    public static Day6NotificationFragment newInstance(){return new Day6NotificationFragment();}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.day6_notification_fragment, container, false);
        counterTimeView = rootView.findViewById(R.id.count_down);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().startService(new Intent(requireActivity(), TimerService.class));
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(timeTikBroadcastReceiver
                , new IntentFilter(TimerService.BROADCAST_TIME_TIK_ACTION));
    }

    @Override
    public void onPause() {
        getActivity().unregisterReceiver(timeTikBroadcastReceiver);
        super.onPause();
    }
}
