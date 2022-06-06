package com.example.basicandroid.day8;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.basicandroid.R;

public class Day8MainFragment extends Fragment {

    public Day8MainFragment() {
        // Required empty public constructor
    }

    public static Day8MainFragment newInstance() {
        Day8MainFragment fragment = new Day8MainFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.day8_main_fragment, container, false);
    }
}