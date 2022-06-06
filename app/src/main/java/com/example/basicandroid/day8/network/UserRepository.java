package com.example.basicandroid.day8.network;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.MutableLiveData;

import com.example.basicandroid.day8.network.model.User;
import com.example.basicandroid.day8.network.retrofit.MyApiEndPointInterface;
import com.example.basicandroid.day8.network.retrofit.RetrofitInstance;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserRepository {
    private MyApiEndPointInterface API;
    private MutableLiveData<List<User>> allUsers = new MutableLiveData<>();

    private final ExecutorService networkExecutor =
            Executors.newFixedThreadPool(4);
    private final Executor mainThread = new Executor() {
        private Handler handler = new Handler(Looper.getMainLooper());
        @Override
        public void execute(Runnable command) {
            handler.post(command);
        }
    };

    public UserRepository(Application application){
        RetrofitInstance retrofitInstance = new RetrofitInstance();
        API = retrofitInstance.getAPI();
    }

    public void getUserFromNetwork(){
        networkExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    List<User> usersFromNetwork = API.getUsers().execute().body();
                    mainThread.execute(new Runnable() {
                        @Override
                        public void run() {
                            allUsers.setValue(usersFromNetwork);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    MutableLiveData<List<User>> getAllUsers(){
        if (allUsers.getValue() == null || allUsers.getValue().isEmpty())
            getUserFromNetwork();
        return allUsers;
    }



}
