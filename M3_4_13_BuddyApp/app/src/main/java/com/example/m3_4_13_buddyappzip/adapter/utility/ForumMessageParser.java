package com.example.m3_4_13_buddyappzip.utility;

import com.example.m3_4_13_buddyappzip.components.ForumMessage;
import com.example.m3_4_13_buddyappzip.components.Post;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ForumMessageParser {
	public List<ForumMessage> parseMessagesOfPost(InputStream inputStream, String postTitle) {
		Gson gson = new Gson();
		InputStreamReader reader = new InputStreamReader(inputStream);
		Type listType = new TypeToken<ArrayList<Post>>(){}.getType();

		List<Post> posts = gson.fromJson(reader, listType);
		for (Post post : posts) {
			JsonObject courseObject = gson.toJsonTree(post).getAsJsonObject().getAsJsonObject("course");
			JsonArray messagesJsonArray = courseObject.getAsJsonArray("forumMessages");

			if (messagesJsonArray != null) {
				List<ForumMessage> messages = new ArrayList<>();
				for (JsonElement element : messagesJsonArray) {
					ForumMessage message = gson.fromJson(element, ForumMessage.class);
					messages.add(message);
				}
				post.setForumMessages(messages);
			}
		}
		Post post = posts.stream()
				.filter(c -> c.getPostTitle().equals(postTitle))
				.findFirst()
				.orElse(null);


		if (post != null) {

			return post.getMessages();
		} else {
			return new ArrayList<>(); // Return an empty list if no matching post is found
		}
	}


}
