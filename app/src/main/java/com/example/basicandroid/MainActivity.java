package com.example.basicandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.basicandroid.day2.Day2Activity;
import com.example.basicandroid.day3.Day3Activity;
import com.example.basicandroid.day4.Day4Activity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void day2(View view){
        Intent intent = new Intent(MainActivity.this, Day2Activity.class);
        startActivity(intent);
    }

    public void day3(View view){
        Intent intent = new Intent(MainActivity.this, Day3Activity.class);
        startActivity(intent);
    }

    public void day4(View view){
        Intent intent = new Intent(MainActivity.this, Day4Activity.class);
        startActivity(intent);
    }
}
