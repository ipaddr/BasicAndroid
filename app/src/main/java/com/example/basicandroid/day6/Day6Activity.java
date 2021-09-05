package com.example.basicandroid.day6;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.basicandroid.R;
import com.example.basicandroid.day5.Day5MainFragment;

public class Day6Activity extends AppCompatActivity {

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

}
