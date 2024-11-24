package com.example.m3_4_13_buddyappzip.adapter.utility;

import com.example.m3_4_13_buddyappzip.components.LearnGroup;
import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class LearnGroupParser {
	public LearnGroup parseLearnGroupFromJson(String json) {
		Gson gson = new Gson();
		return gson.fromJson(json, LearnGroup.class);
	}

	public List<LearnGroup> parseLearnGroupsFromJson(InputStream inputStream) {
		Gson gson = new Gson();
		InputStreamReader reader = new InputStreamReader(inputStream);
		LearnGroup[] learnGroupArray = gson.fromJson(reader, LearnGroup[].class);
		List<LearnGroup> learnGroupList= Arrays.asList(learnGroupArray);
		return learnGroupList;
	}
}
