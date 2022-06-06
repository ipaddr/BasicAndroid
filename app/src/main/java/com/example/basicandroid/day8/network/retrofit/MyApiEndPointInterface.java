package com.example.basicandroid.day8.network.retrofit;

import com.example.basicandroid.day8.network.model.GuestResponse;
import com.example.basicandroid.day8.network.model.User;
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


    String BASE_URL_2 = "https://reqres.in";

    @GET("/api/users")
    Call<GuestResponse> getUsers2();

}
