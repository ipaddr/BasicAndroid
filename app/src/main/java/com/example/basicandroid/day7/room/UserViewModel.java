package com.example.basicandroid.day7.room;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    private final LiveData<List<User>> allUsers;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        allUsers = userRepository.getAllUsers();
    }

    public LiveData<List<User>> getAllUsers(){
        return allUsers;
    }
    public void insert(User user){
        userRepository.insert(user);
    }
    public void update(User user){
        userRepository.update(user);
    }
    public void delete(User user){
        userRepository.delete(user);
    }
}
