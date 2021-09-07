package com.example.basicandroid.day6;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.basicandroid.BaseActivity;
import com.example.basicandroid.R;
import com.example.basicandroid.day6.datastorage.Day6ExternalStorageFragment;
import com.example.basicandroid.day6.datastorage.Day6SharedPreferenceFragment;
import com.example.basicandroid.day6.datastorage.Day6InternalStorageFragment;
import com.example.basicandroid.day6.notification.Day6NotificationAlarmFragment;
import com.example.basicandroid.day6.notification.Day6NotificationFragment;

public class Day6Activity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_every_day);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container_every_day, Day6MainFragment.newInstance())
                    .commitNow();
        }
    }

    @Override
    public void onBackPressed() {
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.container_every_day);
        if (f instanceof Day6MainFragment)
            super.onBackPressed();
        else{
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container_every_day, Day6MainFragment.newInstance())
                    .commitNow();
        }
    }
    public void notification(View view){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_every_day, Day6NotificationFragment.newInstance())
                .commitNow();
    }

    public void notificationAlarm(View view){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_every_day, Day6NotificationAlarmFragment.newInstance())
                .commitNow();
    }

    public void sharedpreference(View view){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_every_day, Day6SharedPreferenceFragment.newInstance())
                .commitNow();
    }

    public void internalstorage(View view){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_every_day, Day6InternalStorageFragment.newInstance())
                .commitNow();
    }

    public void externalstorage(View view){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_every_day, Day6ExternalStorageFragment.newInstance())
                .commitNow();
    }

}
