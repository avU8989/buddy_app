package com.example.m3_4_13_buddyappzip.adapter.utility;

import android.content.Context;

import com.example.m3_4_13_buddyappzip.components.LearnBuddy;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

public class LearnBuddyJsonParser {
	public LearnBuddy parseLearnBuddyFromJson(String json) {
		Gson gson = new Gson();
		return gson.fromJson(json, LearnBuddy.class);
	}

	public List<LearnBuddy> parseLearnBuddiesFromJson(InputStream inputStream) {
		Gson gson = new Gson();
		InputStreamReader reader = new InputStreamReader(inputStream);
		LearnBuddy[] learnBuddyArray = gson.fromJson(reader, LearnBuddy[].class);
		List<LearnBuddy> learnBuddyList = Arrays.asList(learnBuddyArray);
		return learnBuddyList;
	}
}
