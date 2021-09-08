package com.example.basicandroid.day7.sessionmanagement;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.basicandroid.MainActivity;
import com.example.basicandroid.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Base64;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Day7SessionManagementFragment extends Fragment {

    private View rootView;
    private TextInputLayout tilUsername;
    private TextInputLayout tilPassword;
    private Button btnLogin;
    private Button btnLogout;
    private ProgressBar pb;

    private String username;
    private String password;

    private Executor backgroundThread = Executors.newSingleThreadExecutor();
    private Executor mainThread = new Executor() {
        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());
        @Override
        public void execute(Runnable command) {
            mainThreadHandler.post(command);
        }
    };

    public static Day7SessionManagementFragment newInstance(){return new Day7SessionManagementFragment();}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.day7_session_management, container, false);

        pb = rootView.findViewById(R.id.progressBar);
        tilUsername = rootView.findViewById(R.id.user_name);
        tilPassword = rootView.findViewById(R.id.user_password);

        btnLogin = rootView.findViewById(R.id.login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });

        btnLogout = rootView.findViewById(R.id.logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionManagerUtil.getInstance().endUserSession(requireActivity());
                startMainActivity();
            }
        });

        return rootView;
    }

    private void validate(){

        username = tilUsername.getEditText().getText().toString();
        password = tilPassword.getEditText().getText().toString();

        if (TextUtils.isEmpty(username) && TextUtils.isEmpty(password)) {
            Toast.makeText(getActivity(), "Username dan password tidak boleh kosong!!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (username.equalsIgnoreCase("user") && password.equalsIgnoreCase("pass")) {
            login();
        } else {
            Toast.makeText(getActivity(), "Username dan password tidak cocok!!", Toast.LENGTH_SHORT).show();
            return;
        }

    }

    private void login(){
        pb.setVisibility(View.VISIBLE);
        backgroundThread.execute(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void run() {
                // connect server
                SystemClock.sleep(3000);
                mainThread.execute(new Runnable() {
                    @Override
                    public void run() {
                        pb.setVisibility(View.INVISIBLE);
                        startAndStoreSession();
                        startMainActivity();
                    }
                });
            }
        });
    }


    private String generateToken(String username, String password){
        String feeds = username+":"+password;
        String token = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            token = Base64.getEncoder().encodeToString(feeds.getBytes());
        } else {
            token = feeds;
        }
        return token;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void startAndStoreSession(){
        SessionManagerUtil.getInstance()
                .storeUserToken(requireActivity(), generateToken(username, password));
        SessionManagerUtil.getInstance()
                .startUserSession(requireActivity(), 30);
    }

    private void startMainActivity(){
        Intent intent = new Intent(requireActivity(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        requireActivity().startActivity(intent);
    }
}
