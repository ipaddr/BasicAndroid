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

public class UserUpdateFragment extends Fragment {

    private UserViewModel userViewModel;
    private TextInputLayout roomTextInputLayoutLastName;
    private String firstName;
    private TextInputLayout roomTextInputLayoutFirstName;
    private String lastName;
    private Button roomSaveUser;

    public static final String UID = "UID";
    public static final String FIRST_NAME = "FIRST_NAME";
    public static final String LAST_NAME = "LAST_NAME";

    private int uid;
    private String firstNameFromDialog;
    private String lastNameFromDialgo;

    public static UserUpdateFragment newInstance(int uid, String firstName, String lastName) {
        Bundle args = new Bundle();
        args.putInt(UID, uid);
        args.putString(FIRST_NAME, firstName);
        args.putString(LAST_NAME, lastName);
        UserUpdateFragment userUpdateFragment = new UserUpdateFragment();
        userUpdateFragment.setArguments(args);
        return userUpdateFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);

        if (getArguments().containsKey(UID)){
            uid = getArguments().getInt(UID);
            firstNameFromDialog = getArguments().getString(FIRST_NAME);
            lastNameFromDialgo = getArguments().getString(LAST_NAME);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.day5_room_user_input, container, false);
        roomTextInputLayoutFirstName = view.findViewById(R.id.roomTextInputLayoutFirstName);
        roomTextInputLayoutFirstName.getEditText().setText(firstNameFromDialog);

        roomTextInputLayoutLastName = view.findViewById(R.id.roomTextInputLayoutLastName);
        roomTextInputLayoutLastName.getEditText().setText(lastNameFromDialgo);

        roomSaveUser = view.findViewById(R.id.roomSaveUser);
        roomSaveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstName = roomTextInputLayoutFirstName.getEditText().getText().toString();
                lastName = roomTextInputLayoutLastName.getEditText().getText().toString();
                User user = new User();
                user.uid = uid;
                user.firstName = firstName;
                user.lastName = lastName;
                userViewModel.update(user);
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
