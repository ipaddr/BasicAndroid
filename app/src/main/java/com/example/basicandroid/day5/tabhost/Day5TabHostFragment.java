package com.example.basicandroid.day5.tabhost;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.basicandroid.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class Day5TabHostFragment extends Fragment {

    private View root;
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private SampleFragmentPagerAdapter pagerAdapter;

    public static Day5TabHostFragment newInstance() {
        return new Day5TabHostFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.day5_tab_host, container, false);
        tabLayout = root.findViewById(R.id.tabLayout);
        viewPager2 = root.findViewById(R.id.viewpager);
        pagerAdapter = new SampleFragmentPagerAdapter(this);
        viewPager2.setAdapter(pagerAdapter);
        new TabLayoutMediator(tabLayout, viewPager2,
                (tab, position) -> tab.setText("Tab " + (position + 1))
        ).attach();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
