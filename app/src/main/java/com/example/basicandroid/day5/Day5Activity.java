package com.example.basicandroid.day5;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.basicandroid.BaseActivity;
import com.example.basicandroid.R;
import com.example.basicandroid.day3.menu.Day3MainFragment;
import com.example.basicandroid.day5.room.ui.Day5RoomFragment;
import com.example.basicandroid.day5.room.ui.UserInputFragment;

public class Day5Activity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_every_day);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container_every_day, Day3MainFragment.newInstance())
                    .commitNow();
        }
    }

    @Override
    public void onBackPressed() {
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.container_every_day);
        if (f instanceof Day3MainFragment)
            super.onBackPressed();
        else if(f instanceof UserInputFragment){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container_every_day, Day5RoomFragment.newInstance())
                    .commitNow();
        }
        else{
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container_every_day, Day3MainFragment.newInstance())
                    .commitNow();
        }
    }

    public void room(View view){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_every_day, Day5RoomFragment.newInstance())
                .commitNow();
    }
}
