package com.example.basicandroid.day8;

import com.example.basicandroid.day8.model.User;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MyApiEndPointInterface {

    // Trailing slash is needed
    String BASE_URL = "https://jsonplaceholder.typicode.com";

    @GET("/users")
    Call<List<User>> getUsers();

    @GET("/users/{id}")
    Call<User> getUser(@Path("id") String id);

}
