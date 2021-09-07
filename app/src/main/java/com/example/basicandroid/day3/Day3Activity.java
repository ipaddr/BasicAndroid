package com.example.basicandroid.day3;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.basicandroid.BaseActivity;
import com.example.basicandroid.R;
import com.example.basicandroid.day3.adapter.SampleCustomAdapterFragment;

public class Day3Activity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day3);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.day3_container, Day3MainFragment.newInstance())
                    .commitNow();
        }
    }

    @Override
    public void onBackPressed() {
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.day3_container);
        if (f instanceof Day3MainFragment)
            super.onBackPressed();
        else{
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.day3_container, Day3MainFragment.newInstance())
                    .commitNow();
        }
    }

    public void relativeLayout(View view){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.day3_container, RelativeLayoutFragment.newInstance())
                .commitNow();
    }

    public void linearLayout(View view){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.day3_container, LinearLayoutFragment.newInstance())
                .commitNow();
    }

    public void tableLayout(View view){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.day3_container, TableLayoutFragment.newInstance())
                .commitNow();
    }

    public void gridLayout(View view){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.day3_container, GridLayoutFragment.newInstance())
                .commitNow();
    }

    public void baseAdapter(View view){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.day3_container, SampleCustomAdapterFragment.newInstance())
                .commitNow();
    }
}
