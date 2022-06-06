package com.example.basicandroid.day8;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.basicandroid.R;
import com.example.basicandroid.day8.sessionmanagement.SessionManagementFragment;
import com.example.basicandroid.day8.network.ui.Day8UserFragment;

public class Day8Activity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_every_day);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container_every_day, Day8MainFragment.newInstance())
                    .commitNow();
        }
    }

    private void changeFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_every_day, fragment)
                .commitNow();
    }

    public void session(View view){
        changeFragment(new SessionManagementFragment());
    }

    public void network(View view){
        changeFragment(new Day8UserFragment());
    }
}
