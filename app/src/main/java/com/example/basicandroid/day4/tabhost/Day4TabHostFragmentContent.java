package com.example.basicandroid.day4.tabhost;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.basicandroid.R;

public class Day4TabHostFragmentContent extends Fragment {

    private static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;

    public static Day4TabHostFragmentContent newInstance(int page){
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        Day4TabHostFragmentContent day4TabHostFragmentContent = new Day4TabHostFragmentContent();
        day4TabHostFragmentContent.setArguments(args);
        return day4TabHostFragmentContent;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.day4_tab_host_content, container, false);
        TextView tv = root.findViewById(R.id.tab_host_content);
        tv.setText("Fragment #"+mPage);
        return root;
    }
}
