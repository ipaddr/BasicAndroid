package com.example.basicandroid.day5;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.basicandroid.BaseActivity;
import com.example.basicandroid.R;
import com.example.basicandroid.day5.adapter.SampleCustomAdapterFragment;
import com.example.basicandroid.day3.menu.Day3MainFragment;
import com.example.basicandroid.day5.recyclerview.RecyclerViewFragment;
import com.example.basicandroid.day5.tabhost.Day5TabHostFragment;
import com.google.android.material.snackbar.Snackbar;

public class Day5Activity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_every_day);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container_every_day, Day3MainFragment.newInstance())
                    .commitNow();
        }
    }

    @Override
    public void onBackPressed() {
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.container_every_day);
        if (f instanceof Day5MainFragment)
            super.onBackPressed();
        else{
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container_every_day, Day5MainFragment.newInstance())
                    .commitNow();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_show_snackbar:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void baseAdapter(View view){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_every_day, SampleCustomAdapterFragment.newInstance())
                .commitNow();
    }

    public void recyclerView(View view){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_every_day, RecyclerViewFragment.newInstance())
                .commitNow();
    }

    public void webView(View view){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_every_day, Day5WebViewFragment.newInstance())
                .commitNow();
    }

    public void searchView(View view){
        Toast.makeText(this, "Please check SearchView implementation at RecyclerView Section!", Toast.LENGTH_SHORT).show();
    }

    public void tabHost(View view){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_every_day, Day5TabHostFragment.newInstance())
                .commitNow();
    }

    public void nestedScrollView(View view){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_every_day, Day5NestedScrollViewFragment.newInstance())
                .commitNow();
    }

    public void cardView(View view){
        final Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content).getRootView()
                , "Please check CardView implementation at RecyclerView section!", Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("OK", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });
        snackbar.show();
    }
}
