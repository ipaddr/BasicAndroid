package com.example.basicandroid.day7;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.basicandroid.R;
import com.example.basicandroid.day4.background.Day7MultiThreadFragment;
import com.example.basicandroid.day7.room.ui.RoomFragment;
import com.example.basicandroid.day7.room.ui.UserInputFragment;
import com.example.basicandroid.day8.sessionmanagement.SessionManagementFragment;

public class Day7Activity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_every_day);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        else if(f instanceof UserInputFragment){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container_every_day, Day7MainFragment.newInstance())
                    .commitNow();
        }
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

    public void room(View view){
        changeFragment(new RoomFragment());
    }

}
