package com.example.m3_4_13_buddyappzip.utility;

import com.example.m3_4_13_buddyappzip.components.Course;
import com.example.m3_4_13_buddyappzip.components.ForumMessage;
import com.example.m3_4_13_buddyappzip.components.LearnGroup;
import com.example.m3_4_13_buddyappzip.components.Post;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PostJsonParser {
	public Post parsePostFromJson(String json) {
		Gson gson = new Gson();
		return gson.fromJson(json, Post.class);
	}

	public List<Post> parsePostsFromJson(InputStream inputStream) {
		Gson gson = new Gson();
		InputStreamReader reader = new InputStreamReader(inputStream);
		Post[] postsArray = gson.fromJson(reader, Post[].class);
		List<Post> posts= Arrays.asList(postsArray);
		for (Post post : posts) {
			JsonArray messagesJsonArray = gson.toJsonTree(post.getMessages()).getAsJsonArray();
			List<ForumMessage> messages = new ArrayList<>();
			for (JsonElement element : messagesJsonArray) {
				ForumMessage message = gson.fromJson(element, ForumMessage.class);
				messages.add(message);
			}
			post.setForumMessages(messages);
		}
		return posts;
	}

	public List<Post> parsePostsBasedOnStudyFieldFromJson(InputStream inputStream, String studyField) {
		Gson gson = new Gson();
		InputStreamReader reader = new InputStreamReader(inputStream);
		Type listType = new TypeToken<ArrayList<Post>>(){}.getType();

		List<Post> posts = gson.fromJson(reader, listType);
		for (Post post : posts) {
			JsonArray messagesJsonArray = gson.toJsonTree(post.getMessages()).getAsJsonArray();
			List<ForumMessage> messages = new ArrayList<>();
			for (JsonElement element : messagesJsonArray) {
				ForumMessage message = gson.fromJson(element, ForumMessage.class);
				messages.add(message);
			}
			post.setForumMessages(messages);
		}
		return posts.stream().filter(c -> c.getCourse().getCourseName().equals(studyField)).collect(Collectors.toList());
	}
}
