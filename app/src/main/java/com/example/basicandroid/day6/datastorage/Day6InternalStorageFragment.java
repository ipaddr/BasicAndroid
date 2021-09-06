package com.example.basicandroid.day6.datastorage;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.basicandroid.R;
import com.google.android.material.textfield.TextInputLayout;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Day6InternalStorageFragment extends Fragment {

    public static final String LOG_TAG = "Day6ExternalStorageFragment";

    public static final String FILE_NAME = "FILE_NAME.txt";
    public static final String FILE_NAME_CACHE = "FILE_NAME_CACHE.txt";

    private File file, fileCache;

    private View rootView;
    private TextInputLayout keyView;
    private TextInputLayout valueView;
    private Button saveButton;
    private Button readButton;
    private TextView displaySharedPreferenceData;

    public static Day6InternalStorageFragment newInstance(){ return new Day6InternalStorageFragment();}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFile(FILE_NAME);
        initFileCache(FILE_NAME_CACHE);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.day6_sharedpreference_fragment, container, false);

        keyView = rootView.findViewById(R.id.spTextInputLayout);
        keyView.setHint("Add to Cache File");
        valueView = rootView.findViewById(R.id.spTextInputLayout2);
        valueView.setHint("Add to Persistence File");

        saveButton = rootView.findViewById(R.id.spButtonSave);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cache = keyView.getEditText().getText().toString();
                FileHelper.writeFile(getCacheFile(FILE_NAME_CACHE), cache);

                String persistence = valueView.getEditText().getText().toString();
                FileHelper.writeFileByFileName(requireActivity(), FILE_NAME, persistence);

            }
        });

        readButton = rootView.findViewById(R.id.spButtonRead);
        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (file != null && fileCache != null) {
                    String persistence = FileHelper.readFileByFileName(requireActivity(), FILE_NAME);
                    String cache = FileHelper.readFile(getCacheFile(FILE_NAME_CACHE));

                    String text = "From Cache File : " + cache + "\n" +
                            "From Persistence File : " + persistence;

                    displaySharedPreferenceData.setText(text);
                }
            }
        });

        displaySharedPreferenceData = rootView.findViewById(R.id.spTVFromFile);

        return rootView;
    }

    //region internal storage
    private void initFile(String fileName){
        file = new File(requireActivity().getFilesDir(), fileName);
    }

    private void initFileCache(String fileName){
        try {
            fileCache = File.createTempFile(fileName, null,
                    requireActivity().getCacheDir());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String[] allFiles(){
        return requireActivity().fileList();
    }

    private File getCacheFile(String fileName){
        File file = new File(requireActivity().getCacheDir(), fileName);
        return file;
    }
    //endregion
}
