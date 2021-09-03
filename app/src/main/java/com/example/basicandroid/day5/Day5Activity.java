package com.example.basicandroid.day5;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.basicandroid.R;

public class Day5Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_every_day);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container_every_day, Day5MainFragment.newInstance())
                    .commitNow();
        }
    }

    public void menu(View view){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_every_day, Day5MenuFragment.newInstance())
                .commitNow();
    }
}
