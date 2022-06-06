package com.example.basicandroid.day8.network.model;

import com.google.gson.annotations.SerializedName;

public class Support{

	@SerializedName("text")
	private String text;

	@SerializedName("url")
	private String url;

	public String getText(){
		return text;
	}

	public String getUrl(){
		return url;
	}
}