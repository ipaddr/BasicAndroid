package com.example.basicandroid.day7;

import android.os.Bundle;
import android.os.Looper;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.basicandroid.R;
import com.example.basicandroid.day6.Day6MainFragment;
import com.example.basicandroid.day7.sessionmanagement.Day7SessionManagementFragment;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Day7Activity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_every_day);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        Looper looper;

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container_every_day, Day7MainFragment.newInstance())
                    .commitNow();
        }
    }

    @Override
    public void onBackPressed() {
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.container_every_day);
        if (f instanceof Day7MainFragment)
            super.onBackPressed();
        else{
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container_every_day, Day7MainFragment.newInstance())
                    .commitNow();
        }
    }

    private void changeFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_every_day, fragment)
                .commitNow();
    }

    public void sessionmanagement(View view){
        changeFragment(new Day7SessionManagementFragment());
    }

    public void multithreading(View view){

    }

    public void handlermessagequeue(View view){

    }

    public void asynctask(View view){

    }

}
