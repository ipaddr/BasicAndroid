package com.example.basicandroid.day5.room;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class UserRepository {

    private UserDao userDao;
    private LiveData<List<User>> allUsers;

    public UserRepository(Application application){
        AppDatabase database = AppDatabase.getDatabase(application);
        userDao = database.userDao();
        allUsers = userDao.getAll();
    }

    LiveData<List<User>> getAllUsers(){
        return allUsers;
    }

    void insert(User user){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userDao.insert(user);
            }
        });
    }

    void update(User user){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userDao.update(user);
            }
        });
    }

    void delete(User user){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userDao.delete(user);
            }
        });
    }
}
