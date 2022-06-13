package com.example.basicandroid.day5.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.basicandroid.R;

import java.util.ArrayList;

public class CustomListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Item> items;
    public CustomListAdapter(Context context, ArrayList<Item> items) {
        this.context = context;
        this.items = items;
    }
    @Override
    public int getCount() {
        return items.size();
    }
    @Override
    public Object getItem(int position) {
        return items.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.day3_list_view_row_item, parent, false);
        }
        Item currentItem = (Item) getItem(position);
        TextView data = convertView.findViewById(R.id.text_view_item_data);
        data.setText(currentItem.getItemName());
        TextView description = convertView.findViewById(R.id.text_view_item_description);
        description.setText(currentItem.getItemDescription());
        return convertView;
    }

}
