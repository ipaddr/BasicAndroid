package com.example.basicandroid.day5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.basicandroid.R;

public class Day5NestedScrollViewFragment extends Fragment {
    public static Day5NestedScrollViewFragment newInstance() {
        return new Day5NestedScrollViewFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.day4_nested_scroll_view, container, false);
    }
}

