package com.example.basicandroid.day4;

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
import com.example.basicandroid.day4.recyclerview.Item;
import com.example.basicandroid.day4.recyclerview.ItemAdapter;
import com.example.basicandroid.day4.recyclerview.RecyclerViewFragment;

public class Day4SearchViewFragment extends Fragment {
    public static Day4SearchViewFragment newInstance() {
        return new Day4SearchViewFragment();
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
