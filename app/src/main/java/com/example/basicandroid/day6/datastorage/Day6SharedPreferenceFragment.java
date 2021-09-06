package com.example.basicandroid.day6.datastorage;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.basicandroid.R;
import com.google.android.material.textfield.TextInputLayout;

import java.io.File;
import java.util.Map;

public class Day6SharedPreferenceFragment extends Fragment {

    public static final String SHARED_PREFERENCE_NAME = "com.example.basicandroid.day6.datastorage.Day6SharedPreferenceFragment";

    private SharedPreferences sharedPreferences;

    private View rootView;
    private TextInputLayout keyView;
    private TextInputLayout valueView;
    private Button saveButton;
    private Button readButton;
    private TextView displaySharedPreferenceData;

    public static Day6SharedPreferenceFragment newInstance(){ return new Day6SharedPreferenceFragment();}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = requireActivity().getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.day6_sharedpreference_fragment, container, false);

        keyView = rootView.findViewById(R.id.spTextInputLayout);
        valueView = rootView.findViewById(R.id.spTextInputLayout2);

        saveButton = rootView.findViewById(R.id.spButtonSave);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = keyView.getEditText().getText().toString();
                String value = valueView.getEditText().getText().toString();
                saveDataToSharedPreferenceAsync(key, value);
            }
        });

        readButton = rootView.findViewById(R.id.spButtonRead);
        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readSavedDataFromSharedPreference();
            }
        });

        displaySharedPreferenceData = rootView.findViewById(R.id.spTVFromFile);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        readSavedDataFromSharedPreference();
    }

    private void saveDataToSharedPreferenceAsync(String key, String value){
        sharedPreferences.edit().putString(key, value).apply();
    }

    private void readSavedDataFromSharedPreference(){
        String text = "";
        Map<String, ?> allEntries = sharedPreferences.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            text += "Key :"+ entry.getKey() + " ; Value : " + entry.getValue().toString() + "\n";
        }
        displaySharedPreferenceData.setText(text);
    }
}
