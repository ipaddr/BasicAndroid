package com.example.basicandroid.day3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.basicandroid.BaseActivity;
import com.example.basicandroid.R;
import com.example.basicandroid.day5.adapter.SampleCustomAdapterFragment;
import com.example.basicandroid.day3.menu.Day3MenuFragment;

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

    public void constranitLayout(View view){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.day3_container, ConstraintLayoutFragment.newInstance())
                .commitNow();
    }

    public void baseAdapter(View view){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.day3_container, SampleCustomAdapterFragment.newInstance())
                .commitNow();
    }

    public void menu(View view){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.day3_container, Day3MenuFragment.newInstance())
                .commitNow();
    }

    private boolean tableFlag = false;
    public void tableCollapse(View view){
        TableLayout tableLayout = findViewById(R.id.tabLayout);
        Button btn = findViewById(R.id.tablebutton);

        tableLayout.setColumnCollapsed(3, tableFlag);
        tableLayout.setColumnCollapsed(4, tableFlag);

        if (tableFlag){
            tableFlag = false;
            btn.setText("Show Detail");
        } else {
            tableFlag = true;
            btn.setText("Hide Detail");
        }
    }
}
