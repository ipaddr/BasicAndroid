package com.example.basicandroid.day8;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.basicandroid.day8.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserViewModel extends AndroidViewModel {

    private RetrofitInstance retrofitInstance;

    public UserViewModel(@NonNull Application application) {
        super(application);
        retrofitInstance = new RetrofitInstance();
    }

    public RetrofitInstance getRetrofitInstance(){
        return  retrofitInstance;
    }

}
