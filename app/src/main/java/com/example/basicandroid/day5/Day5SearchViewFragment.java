package com.example.basicandroid.day5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basicandroid.R;
import com.example.basicandroid.day5.recyclerview.Item;
import com.example.basicandroid.day5.recyclerview.ItemAdapter;

public class Day5SearchViewFragment extends Fragment {
    public static Day5SearchViewFragment newInstance() {
        return new Day5SearchViewFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.day4_recyclerview_container, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.recyclerview);
        ItemAdapter itemAdapter = new ItemAdapter(Item.generateDateItem());
        recyclerView.setAdapter(itemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return root;
    }
}
