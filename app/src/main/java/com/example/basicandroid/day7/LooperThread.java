package com.example.basicandroid.day7;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;


class LooperThread extends Thread {
    public Handler mHandler;

    public void run() {
        Looper.getMainLooper();

        mHandler = new Handler() {
            public void handleMessage(Message msg) {
                // process incoming messages here
                // this will run in non-ui/background thread
            }
        };

        Looper.loop();
    }
}
