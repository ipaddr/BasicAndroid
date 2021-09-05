package com.example.basicandroid.day5.room.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.basicandroid.R;
import com.example.basicandroid.day5.room.User;
import com.example.basicandroid.day5.room.UserViewModel;
import com.google.android.material.textfield.TextInputLayout;

public class UserInputFragment extends Fragment {

    private UserViewModel userViewModel;
    private TextInputLayout roomTextInputLayoutLastName;
    private String firstName;
    private TextInputLayout roomTextInputLayoutFirstName;
    private String lastName;
    private Button roomSaveUser;

    public static UserInputFragment newInstance() {
        return new UserInputFragment();
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
        View view = inflater.inflate(R.layout.day5_room_user_input, container, false);
        roomTextInputLayoutFirstName = view.findViewById(R.id.roomTextInputLayoutFirstName);
        roomTextInputLayoutLastName = view.findViewById(R.id.roomTextInputLayoutLastName);
        roomSaveUser = view.findViewById(R.id.roomSaveUser);
        roomSaveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstName = roomTextInputLayoutFirstName.getEditText().getText().toString();
                lastName = roomTextInputLayoutLastName.getEditText().getText().toString();
                User user = new User();
                user.firstName = firstName;
                user.lastName = lastName;
                userViewModel.insert(user);
                backToRoomFragment();
            }
        });
        return view;
    }

    private void backToRoomFragment(){
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_every_day, Day5RoomFragment.newInstance())
                .commitNow();
    }
}
