package com.example.basicandroid.day5;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.basicandroid.R;

public class Day5MenuFragment extends Fragment {

    private TextView contextualMenuMe;
    private TextView popupMenu;

    //region action mode
    private TextView contextualActionModeMe;
    private ActionMode actionMode;
    private ActionMode.Callback actionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            // Inflate a menu resource providing context menu items
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.day5_contextual_menu, menu);
            return true;
        }
        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false; // Return false if nothing is done
        }
        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()){
                case R.id.text_color_red:
                    contextualActionModeMe.setTextColor(Color.RED);
                    return true;
                case R.id.text_color_green:
                    contextualActionModeMe.setTextColor(Color.GREEN);
                    return true;
                case R.id.text_color_blue:
                    contextualActionModeMe.setTextColor(Color.BLUE);
                    return true;
                case R.id.text_color_black:
                    contextualActionModeMe.setTextColor(Color.BLACK);
                    return true;
                default:return false;
            }
        }
        @Override
        public void onDestroyActionMode(ActionMode mode) {
            actionMode = null;
        }
    };
    //endregion

    public static Day5MenuFragment newInstance() {
        return new Day5MenuFragment();
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
        return inflater.inflate(R.layout.day5_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        contextualMenuMe = view.findViewById(R.id.contextual_menu_tv);
        registerForContextMenu(contextualMenuMe);

        contextualActionModeMe = view.findViewById(R.id.contextual_action_mode);
        contextualActionModeMe.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (actionMode != null) {
                    return false;
                }

                // Start the CAB using the ActionMode.Callback defined above
                actionMode = getActivity().startActionMode(actionModeCallback);
                view.setSelected(true);
                return true;
            }
        });

        popupMenu = view.findViewById(R.id.popup_menu);
        popupMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(v);
            }
        });
    }

    //region popup menu
    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(getActivity(), v);

        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.day5_contextual_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.text_color_red:
                        popupMenu.setTextColor(Color.RED);
                        return true;
                    case R.id.text_color_green:
                        popupMenu.setTextColor(Color.GREEN);
                        return true;
                    case R.id.text_color_blue:
                        popupMenu.setTextColor(Color.BLUE);
                        return true;
                    case R.id.text_color_black:
                        popupMenu.setTextColor(Color.BLACK);
                        return true;
                    default:return false;
                }
            }
        });

        popup.show();
    }
    //endregion

    //region contextual menu
    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, 
                                    @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.day5_contextual_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.text_color_red:
                contextualMenuMe.setTextColor(Color.RED);
                return true;
            case R.id.text_color_green:
                contextualMenuMe.setTextColor(Color.GREEN);
                return true;
            case R.id.text_color_blue:
                contextualMenuMe.setTextColor(Color.BLUE);
                return true;
            case R.id.text_color_black:
                contextualMenuMe.setTextColor(Color.BLACK);
                return true;
            default:return super.onContextItemSelected(item);
        }
    }
    //endregion

    //region option menu
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.day5_option_menu, menu);
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (isHide)
            menu.setGroupVisible(R.id.data, false);
        else
            menu.setGroupVisible(R.id.data, true);
    }

    private static boolean isHide = true;
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.on:
                isHide = false;
                getActivity().invalidateOptionsMenu();
                return true;
            case R.id.off:
                isHide = true;
                getActivity().invalidateOptionsMenu();
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }
    //endregion
}
