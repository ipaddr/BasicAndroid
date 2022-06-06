package com.example.basicandroid;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.basicandroid.day2.Day2Activity;
import com.example.basicandroid.day3.Day3Activity;
import com.example.basicandroid.day4.Day4Activity;
import com.example.basicandroid.day5.Day5Activity;
import com.example.basicandroid.day6.Day6Activity;
import com.example.basicandroid.day7.Day7Activity;
import com.example.basicandroid.day8.Day8Activity;
import com.example.basicandroid.day8.network.retrofit.MyApiEndPointInterface;
import com.example.basicandroid.day8.network.retrofit.RetrofitInstance;
import com.example.basicandroid.day8.network.model.GuestResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RetrofitInstance retrofitInstance = new RetrofitInstance(MyApiEndPointInterface.BASE_URL_2);
        retrofitInstance.getAPI().getUsers2().enqueue(new Callback<GuestResponse>() {
            @Override
            public void onResponse(Call<GuestResponse> call, Response<GuestResponse> response) {
                Log.d("","");
            }

            @Override
            public void onFailure(Call<GuestResponse> call, Throwable t) {
                Log.d("","");
            }
        });

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

    public void day5(View view){
        Intent intent = new Intent(MainActivity.this, Day5Activity.class);
        startActivity(intent);
    }

    public void day6(View view){
        Intent intent = new Intent(MainActivity.this, Day6Activity.class);
        startActivity(intent);
    }

    public void day7(View view){
        Intent intent = new Intent(MainActivity.this, Day7Activity.class);
        startActivity(intent);
    }

    public void day8(View view){
        Intent intent = new Intent(MainActivity.this, Day8Activity.class);
        startActivity(intent);
    }
}
