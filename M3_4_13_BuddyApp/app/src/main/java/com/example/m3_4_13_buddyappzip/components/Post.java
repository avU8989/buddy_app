package com.example.m3_4_13_buddyappzip.components;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Post implements Serializable {
    private User user;
    private Course course;
    private String postTitle;
    private String description;
    private int upvote;
    private int downvote;
    private List<ForumMessage> forumMessages;
    private String date;
    private String imageUrl;

    public Post(User user, Course course, String postTitle, String description, int upvote, int downvote, List<ForumMessage> forumMessages, String date){
        this.user = user;
        this.course = course;
        this.postTitle = postTitle;
        this.description = description;
        this.upvote = upvote;
        this.downvote = downvote;
        this.forumMessages = forumMessages;
        this.date = date;
    }

    public User getUser(){
        return this.user;
    }

    public Course getCourse(){
        return this.course;
    }

    public String getPostTitle(){
        return this.postTitle;
    }

    public String getPostDescription(){
        return this.description;
    }

    public int getUpvote(){
        return this.upvote;
    }

    public int getDownvote(){
        return this.downvote;
    }

    public String getImageUrl(){
        return this.imageUrl;
    }

    public List<ForumMessage> getMessages(){
        return this.forumMessages;
    }

    public void setUpvote(int upvotes){
        this.upvote = upvotes;
    }

    public void setDownvote(int downvote){
        this.downvote = downvote;
    }

    @Override
    public String toString() {
        return this.course.getCourseName() + ": " + this.postTitle;
    }

    public void setForumMessages(List<ForumMessage> messages) {
    this.forumMessages = new ArrayList<>(messages);
    }
}
