package com.example.basicandroid.day5.adapter;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.basicandroid.R;

import java.util.ArrayList;
import java.util.Random;

public class SampleCustomAdapterFragment extends Fragment {

    private ArrayList<Item> items = generateDateItem();
    private View root;
    private ListView lv;
    private CustomListAdapter cla;

    public static SampleCustomAdapterFragment newInstance() {
        return new SampleCustomAdapterFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.day3_list_view, container, false);
        cla = new CustomListAdapter(getActivity(), items);
        int size = items.size();
        Log.d("test", String.valueOf(size));
        lv = root.findViewById(R.id.listView);
        lv.setAdapter(cla);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private ArrayList<Item> generateDateItem(){
        ArrayList<Item> datas = new ArrayList<>();
        for(int i=0;i<100;i++){
            Item item = new Item();
            item.setItemName(getSaltString());
            item.setItemDescription(getSaltString());
            datas.add(item);
        }
        Item item = new Item();
        item.setItemName("BCA");
        item.setItemDescription("The Biggest Financial Company in Indonesia");
        datas.add(item);
        return datas;
    }

    // Random data generator
    protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();

        return saltStr;
    }
}
