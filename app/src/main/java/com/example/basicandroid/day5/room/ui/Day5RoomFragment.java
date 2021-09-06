package com.example.basicandroid.day5.room.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basicandroid.R;
import com.example.basicandroid.day4.dialog.FireMissilesDialogFragment;
import com.example.basicandroid.day5.room.User;
import com.example.basicandroid.day5.room.UserViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class Day5RoomFragment extends Fragment {

    private UserViewModel userViewModel;

    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;

    private final UserClickableCallback userClickableCallback = new UserClickableCallback() {
        @Override
        public void onClick(View view, User user) {
            DialogFragment newFragment = DeleteUserDialogFragment.newInstance(user);
            newFragment.show(getChildFragmentManager(), "DeleteUserDialogFragment");
        }
    };

    public static Day5RoomFragment newInstance() {
        return new Day5RoomFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.day5_room_recyclerview, container, false);
        recyclerView = view.findViewById(R.id.roomRecyclerView);
        userAdapter = new UserAdapter(userClickableCallback);
        recyclerView.setAdapter(userAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        fab = view.findViewById(R.id.room_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInput();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        userViewModel.getAllUsers().observe(getViewLifecycleOwner(), users -> {
            if (users != null) {
                userAdapter.submitList(users);
            }
        });
    }

    private void showInput(){
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_every_day, UserInputFragment.newInstance())
                .commitNow();
    }

}
