package com.example.basicandroid.day8.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basicandroid.R;
import com.example.basicandroid.day8.model.User;

public class UserAdapter extends ListAdapter<User, UserAdapter.ViewHolder> {

    private final UserClickableCallback userClickableCallback;

    protected UserAdapter(@NonNull UserClickableCallback userClickableCallback) {
        super(new AsyncDifferConfig.Builder<>(new DiffUtil.ItemCallback<User>() {
            @Override
            public boolean areItemsTheSame(@NonNull User oldItem, @NonNull User newItem) {
                return oldItem.getId() == newItem.getId();
            }
            @Override
            public boolean areContentsTheSame(@NonNull User oldItem, @NonNull User newItem) {
                return oldItem.getId() == newItem.getId();
            }
        }).build());
        this.userClickableCallback = userClickableCallback;
    }


    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.day5_room_recyclerview_item, parent, false);
        UserAdapter.ViewHolder viewHolder = new UserAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        holder.tvFirstName.setText("Name : "+getItem(position).getName());
        holder.tvLastName.setText("Email : "+getItem(position).getEmail());
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvFirstName;
        TextView tvLastName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFirstName = itemView.findViewById(R.id.room_tv_firstname);
            tvLastName = itemView.findViewById(R.id.room_tv_lastname);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAbsoluteAdapterPosition(); // gets item position
            if (position != RecyclerView.NO_POSITION) { // Check if an item was deleted, but the user clicked it before the UI removed it
                User user = getItem(position);
                userClickableCallback.onClick(v, user);
            }
        }
    }

}
