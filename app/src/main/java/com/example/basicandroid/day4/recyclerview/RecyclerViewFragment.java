package com.example.basicandroid.day4.recyclerview;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basicandroid.R;
import com.example.basicandroid.day3.LinearLayoutFragment;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RecyclerViewFragment extends Fragment {

    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    private ArrayList<Item> items = Item.generateDateItem();

    public static RecyclerViewFragment newInstance() {
        return new RecyclerViewFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.day4_recyclerview_container, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerview);
        itemAdapter = new ItemAdapter(items);
        recyclerView.setAdapter(itemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        SearchManager sm = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        SearchView sv = (SearchView) menu.findItem(R.id.search).getActionView();
        sv.setSearchableInfo(sm.getSearchableInfo(getActivity().getComponentName()));
        sv.setIconifiedByDefault(true);
        sv.setMaxWidth(Integer.MAX_VALUE);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                notifyList(s);
                return true;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                if (TextUtils.isEmpty(s)){
                    resetData();
                    return true;
                }
                return false;
            }
        });
    }

    private void resetData(){
        items.clear();
        items.addAll(Item.generateDateItem());
        itemAdapter.notifyDataSetChanged();
    }

    private void notifyList(String query){
        Item item = doSearchItems(query);
        if (item != null) {
            items.clear();
            items.add(item);
            itemAdapter.notifyDataSetChanged();
        }
    }

    private Item doSearchItems(String query){
        Item foundItem = null;
        for (Item item:items) {
            if (item.getName().equalsIgnoreCase(query)) {
                foundItem = item;
                break;
            }
        }
        return foundItem;
    }
}
