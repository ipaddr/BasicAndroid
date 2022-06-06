package com.example.basicandroid.day3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.basicandroid.R;

public class ConstraintLayoutFragment extends Fragment {
    public static ConstraintLayoutFragment newInstance() {
        return new ConstraintLayoutFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.day3_constrain_layout, container, false);
    }
}
