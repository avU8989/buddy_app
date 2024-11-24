package com.example.m3_4_13_buddyappzip.components;


import java.io.Serializable;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class ForumMessage implements Serializable {
	private User user;
	private String text;
	private int upvote;
	private int downvote;
	private String date;


	public ForumMessage(User user, String text, int upvote, int downvote) {
		this.user = user;
		this.text = text;
		this.upvote = upvote;
		this.downvote = downvote;
		this.date = date;
	}
	public ForumMessage(User user, String text, int upvote, int downvote, String date) {
		this.user = user;
		this.text = text;
		this.upvote = upvote;
		this.downvote = downvote;
		this.date = date;
	}


	public User getUser(){
		return this.user;
	}

	public String getPostDescription(){
		return this.text;
	}

	public int getUpvote(){
		return this.upvote;
	}

	public int getDownvote(){
		return this.downvote;
	}

	@Override
	public String toString(){
		return this.text;
	}

	public void setUpvote(int upvotes){
		this.upvote = upvotes;
	}

	public void setDownvote(int downvote){
		this.downvote = downvote;
	}
}
