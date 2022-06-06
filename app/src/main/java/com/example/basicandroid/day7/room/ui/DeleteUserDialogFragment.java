package com.example.basicandroid.day7.room.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.basicandroid.R;
import com.example.basicandroid.day7.room.User;
import com.example.basicandroid.day7.room.UserViewModel;

public class DeleteUserDialogFragment extends DialogFragment {

    private static final String TAG_UID = "TAG_UID";
    private static final String TAG_FIRST_NAME = "TAG_FIRST_NAME";
    private static final String TAG_LAST_NAME = "TAG_LAST_NAME";

    private int uid = -1;
    private String firstName;
    private String lastName;

    private UserViewModel userViewModel;

    public static DeleteUserDialogFragment newInstance(User user){
        Bundle args = new Bundle();
        args.putInt(TAG_UID,user.uid);
        args.putString(TAG_FIRST_NAME, user.firstName);
        args.putString(TAG_LAST_NAME, user.lastName);
        DeleteUserDialogFragment dudf = new DeleteUserDialogFragment();
        dudf.setArguments(args);
        return dudf;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && getArguments().containsKey(TAG_UID)
                && getArguments().containsKey(TAG_FIRST_NAME)
                && getArguments().containsKey(TAG_LAST_NAME)){
            uid = getArguments().getInt(TAG_UID, -1);
            firstName = getArguments().getString(TAG_FIRST_NAME, null);
            lastName = getArguments().getString(TAG_LAST_NAME, null);
        }

        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
    }

    private void deleteUser(){
        if (uid != -1) {
            User user = new User();
            user.uid = uid;
            user.firstName = firstName;
            user.lastName = lastName;
            userViewModel.delete(user);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Do you want to delete "+firstName+"?")
                .setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteUser();
                    }
                })
                .setNegativeButton("UPDATE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container_every_day, UserUpdateFragment.newInstance(uid, firstName, lastName))
                                .commitNow();
                    }
                });
        return builder.create();
    }
}
