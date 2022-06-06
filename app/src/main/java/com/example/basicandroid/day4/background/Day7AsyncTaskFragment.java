package com.example.basicandroid.day4.background;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.basicandroid.R;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Day7AsyncTaskFragment extends Fragment {

    private View rootView;
    private ProgressBar hzProgressBar;
    private Button btnDownload;
    private TextView tvDownload;

    public static Day7AsyncTaskFragment newInstance(){
        return new Day7AsyncTaskFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Thread(){
            @Override
            public void run() {
                super.run();
                // eksekusi code disini
            }
        }.start();

        new Executor() {
            @Override
            public void execute(Runnable command) {
                // eksekusi code disini
            }
        };

        Executors.newFixedThreadPool(10);

        Handler handler = new Handler(Looper.getMainLooper());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.day7_async_task, container, false);
        hzProgressBar = rootView.findViewById(R.id.hzProgresBar);
        btnDownload = rootView.findViewById(R.id.btn_download);
        tvDownload = rootView.findViewById(R.id.tv_download);
        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DownloadTask task = new DownloadTask();
                task.execute("http://file");
            }
        });
        return rootView;
    }

    class DownloadTask extends AsyncTask<String, Integer, String>{

        private int count = 0;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tvDownload.setText("Proses download akan segera dimulai");
        }

        @Override
        protected String doInBackground(String... voids) {
            String s = voids[0];
            for (int i = 0; i < 10; i++) {
                count = count + 10;
                publishProgress(count);
                SystemClock.sleep(2000);
            }
            return "Proses download sudah selesai sebesar : "+count+"%";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            hzProgressBar.setProgress(values[0]);
            tvDownload.setText("Proses "+values[0]+"% download dari server.");
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            tvDownload.setText(s);
        }
    }
}
