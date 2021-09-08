package com.example.basicandroid.day7.background;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.basicandroid.R;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Day7MultiThreadFragment extends Fragment {

    private Executor poolWorker = Executors.newFixedThreadPool(2);
    private Executor mainThread = new Executor() {
        private Handler handler = new Handler(Looper.getMainLooper());
        @Override
        public void execute(Runnable command) {
            handler.post(command);
        }
    };

    private View rootView;
    private TextView threadOne, threadTwo, threadThree, threadFour;
    private Button startButton;

    float multiplication = 2;
    float division = 2;
    float summation = 2;
    float reduction = 2;

    final int base = 2;
    final int step = 20;
    final long clockSleep = 500;

    private Thread workMul = new Thread() {
        @Override
        public void run() {
            for (int i = 0; i < step; i++) {
                multiplication = multiplication * base;
                mainThread.execute(new Runnable() {
                    @Override
                    public void run() {
                        threadOne.setText(String.valueOf(multiplication));
                    }
                });
                SystemClock.sleep(clockSleep);
            }
        }
    };

    private Thread workDiv = new Thread() {
        @Override
        public void run() {
            for (int i = 0; i < step; i++) {
                division = division / base;
                mainThread.execute(new Runnable() {
                    @Override
                    public void run() {
                        threadTwo.setText(String.valueOf(division));
                    }
                });
                SystemClock.sleep(clockSleep);
            }
        }
    };

    private Thread workSum = new Thread() {
        @Override
        public void run() {
            for (int i = 0; i < step; i++) {
                summation = summation + base;
                mainThread.execute(new Runnable() {
                    @Override
                    public void run() {
                        threadThree.setText(String.valueOf(summation));
                    }
                });
                SystemClock.sleep(clockSleep);
            }
        }
    };

    private Thread workSub = new Thread() {
        @Override
        public void run() {
            for (int i = 0; i < step; i++) {
                reduction = reduction - base;
                mainThread.execute(new Runnable() {
                    @Override
                    public void run() {
                        threadFour.setText(String.valueOf(reduction));
                    }
                });
                SystemClock.sleep(clockSleep);
            }
        }
    };

    public static Day7MultiThreadFragment newInstance(){return new Day7MultiThreadFragment();}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.day7_multi_thread, container, false);

        threadOne = rootView.findViewById(R.id.thread_one);
        threadTwo = rootView.findViewById(R.id.thread_two);
        threadThree = rootView.findViewById(R.id.thread_three);
        threadFour = rootView.findViewById(R.id.thread_four);

        startButton = rootView.findViewById(R.id.start_worker);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });

        return rootView;
    }

    public void start(){
        poolWorker.execute(workMul);
        poolWorker.execute(workDiv);
        poolWorker.execute(workSum);
        poolWorker.execute(workSub);
    }
}
