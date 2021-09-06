package com.example.basicandroid.day6.datastorage;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class FileHelper {

    //region read write file
    public static void writeFileByFileName(Context context, String fileName, String text){
        try {
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write(text.getBytes(StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeFile(File file, String text){
        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream(file);
            stream.write(text.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String readFileByFileName(Context context, String fileName){
        FileInputStream fis = null;
        InputStreamReader isr = null;
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        try {
            fis = context.openFileInput(fileName);
            isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
            sb = new StringBuilder();
            br = new BufferedReader(isr);
            String line = br.readLine();
            while (line != null){
                sb.append(line).append("\n");
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    public static String readFile(File file){
        int length = (int) file.length();
        byte[] bytes = new byte[length];
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            in.read(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String contents = new String(bytes);
        return contents;
    }
    //endregion

}
