package com.example.basicandroid.day8.ui;

import android.view.View;

import com.example.basicandroid.day8.model.User;

public interface UserClickableCallback {
    void onClick(View view, User user);
}
