package com.example.basicandroid.day7.room.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basicandroid.R;
import com.example.basicandroid.day7.room.User;
import com.example.basicandroid.day7.room.UserViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class RoomFragment extends Fragment {

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

    public static RoomFragment newInstance() {
        return new RoomFragment();
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
