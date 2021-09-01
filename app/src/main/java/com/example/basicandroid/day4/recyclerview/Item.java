package com.example.basicandroid.day4.recyclerview;

import java.util.ArrayList;
import java.util.Random;

public class Item {
    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static ArrayList<Item> generateDateItem(){
        ArrayList<Item> datas = new ArrayList<>();
        for(int i=0;i<100;i++){
            Item item = new Item();
            item.setName(getSaltName());
            item.setDescription(getSaltName());
            datas.add(item);
        }
        return datas;
    }

    // Random data generator
    private static final String getSaltName() {
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
