package com.example.basicandroid.day8.network.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basicandroid.R;
import com.example.basicandroid.day8.network.UserViewModel;
import com.example.basicandroid.day8.network.model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

public class Day8UserFragment extends Fragment {

    private UserViewModel userViewModel;

    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private ProgressBar pb;

    private final UserClickableCallback userClickableCallback = new UserClickableCallback() {
        @Override
        public void onClick(View view, User user) {
            Gson gson = new Gson();
            String userString = gson.toJson(user);
            Toast.makeText(requireActivity(), userString, Toast.LENGTH_SHORT).show();
        }
    };

    public static Day8UserFragment newInstance() {
        return new Day8UserFragment();
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
        pb = view.findViewById(R.id.rv_pb);
        recyclerView = view.findViewById(R.id.roomRecyclerView);
        userAdapter = new UserAdapter(userClickableCallback);
        recyclerView.setAdapter(userAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        fab = view.findViewById(R.id.room_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userViewModel.getAllUsers().observe(getViewLifecycleOwner(), users -> {
            pb.setVisibility(View.INVISIBLE);
            userAdapter.submitList(users);
        });
    }

}
