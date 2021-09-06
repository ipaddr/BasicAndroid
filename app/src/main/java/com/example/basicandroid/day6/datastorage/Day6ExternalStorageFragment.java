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

import java.io.File;

public class Day6ExternalStorageFragment extends Fragment {

    public static final String LOG_TAG = "Day6ExternalStorageFragment";

    public static final String EXTERNAL_FILE_NAME = "EXTERNAL_FILE_NAME.txt";
    public static final String EXTERNAL_FILE_NAME_CACHE = "EXTERNAL_FILE_NAME_CACHE.txt";
    public static final String EXTERNAL_FILE_NAME_IN_DOCUMENT = "EXTERNAL_FILE_NAME_CACHE.txt";

    private File file, fileCache, fileInDocumentDir;

    private View rootView;
    private TextInputLayout keyView;
    private TextInputLayout valueView;
    private Button saveButton;
    private Button readButton;
    private TextView displaySharedPreferenceData;

    public static Day6ExternalStorageFragment newInstance(){return new Day6ExternalStorageFragment();}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFile();
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
                FileHelper.writeFile(fileCache, cache);

                String persistence = valueView.getEditText().getText().toString();
                FileHelper.writeFile(file, persistence);

                FileHelper.writeFile(fileInDocumentDir, cache+ "\n" +persistence);
            }
        });

        readButton = rootView.findViewById(R.id.spButtonRead);
        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cache = FileHelper.readFile(fileCache);
                String persistence = FileHelper.readFile(file);

                String text = "From Persistence File : "+ persistence + "\n"
                        + "From Cache File : "+ cache;

                displaySharedPreferenceData.setText(text);
            }
        });

        displaySharedPreferenceData = rootView.findViewById(R.id.spTVFromFile);

        return rootView;
    }

    //region external storage
    private void initFile(){
        if (checkExternalStorageAvailability()){
            file = getFileByName(EXTERNAL_FILE_NAME);
            fileCache = getCacheFileByName(EXTERNAL_FILE_NAME_CACHE);
            fileInDocumentDir = getSpecificPublicDirectory(requireActivity(), EXTERNAL_FILE_NAME_IN_DOCUMENT);
        }
    }

    private boolean checkExternalStorageAvailability(){
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    private File getExternalStorage(){
        File[] externalStorageVolumes =
                ContextCompat.getExternalFilesDirs(requireActivity().getApplicationContext(), null);
        File primaryExternalStorage = externalStorageVolumes[0];
        return primaryExternalStorage;
    }

    private File getFileByName(String fileName){
        File appSpecificExternalDir = new File(requireActivity().getExternalFilesDir(null), fileName);
        return appSpecificExternalDir;
    }

    private File getCacheFileByName(String fileName){
        File externalCacheFile = new File(requireActivity().getExternalCacheDir(), fileName);
        return externalCacheFile;
    }

    @Nullable
    File getAppSpecificStorageDir(Context context, String fileName) {
        File file = new File(context.getExternalFilesDir(
                Environment.DIRECTORY_DOCUMENTS), fileName);
        if (file == null || !file.mkdirs()) {
            Log.e(LOG_TAG, "Directory not created");
        }
        return file;
    }

    File getSpecificPublicDirectory(Context context, String fileName){
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), fileName);
        if (file == null || !file.mkdirs()) {
            Log.e(LOG_TAG, "Directory not created");
        }
        return file;
    }
    //endregion
}
