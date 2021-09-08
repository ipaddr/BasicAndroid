package com.example.basicandroid.day8;

import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.basicandroid.R;
import com.example.basicandroid.day7.Day7MainFragment;
import com.example.basicandroid.day7.sessionmanagement.Day7SessionManagementFragment;
import com.example.basicandroid.day8.model.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Day8Activity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_every_day);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MyApiEndPointInterface api = RetrofitInstance.getRetrofitInstance().create(MyApiEndPointInterface.class);
        Call<User> userCall = api.getUser("1");
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d("","");
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("","");
            }
        });

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

}
