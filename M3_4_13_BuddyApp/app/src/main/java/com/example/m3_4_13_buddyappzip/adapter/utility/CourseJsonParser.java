package com.example.m3_4_13_buddyappzip.utility;

import com.example.m3_4_13_buddyappzip.components.Course;
import com.example.m3_4_13_buddyappzip.components.LearnBuddy;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CourseJsonParser {

	public Course parseCourseFromJson(String json) {
		Gson gson = new Gson();
		return gson.fromJson(json, Course.class);
	}

	public List<Course> parseCoursesFromJson(InputStream inputStream) {
		Gson gson = new Gson();
		InputStreamReader reader = new InputStreamReader(inputStream);
		Course[] coursesArray = gson.fromJson(reader, Course[].class);
		List<Course> courses = Arrays.asList(coursesArray);
		return courses;
	}

	public List<Course> parseCourseBasedOnStudyFieldFromJson(InputStream inputStream, String studyField) {
		Gson gson = new Gson();
		InputStreamReader reader = new InputStreamReader(inputStream);
		Type listType = new TypeToken<ArrayList<Course>>(){}.getType();

		List<Course> courses = gson.fromJson(reader, listType);
		return courses.stream().filter(c -> c.getStudyProgram().getStudyField().equals(studyField)).collect(Collectors.toList());
	}
}
