package com.example.basicandroid.day4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.basicandroid.R;
import com.example.basicandroid.day3.Day3MainFragment;
import com.google.android.material.snackbar.Snackbar;

public class Day4MainFragment extends Fragment {
    public static Day4MainFragment newInstance() {
        return new Day4MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.day4_main_fragment, container, false);
    }
}
