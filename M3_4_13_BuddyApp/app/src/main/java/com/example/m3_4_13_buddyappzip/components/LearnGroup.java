package com.example.m3_4_13_buddyappzip.components;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LearnGroup implements Serializable{
    private String groupId;
    private Course course;
    private String groupTitle;
    private String description;
    private String university;
    private String imageUrl;
    private int memberCount;

    public LearnGroup(String groupId, Course course, String groupTitle, String description, String university, String imageUrl){
        this.groupId = groupId;
        this.course = course;
        this.groupTitle = groupTitle;
        this.description = description;
        this.university = university;
        this.memberCount = 1;
    }

    public LearnGroup(String groupId, Course course, String groupTitle, String description, String university, String imageUrl, int memberCount){
        this(groupId, course, groupTitle, description, university, imageUrl);
        this.memberCount = memberCount;
    }


    public String getDescription(){
        return this.description;
    }

    public String getName(){
        return this.groupTitle;
    }

    public String getUniverstity() {
        return this.university;
    }

    public int getMemberCount(){
        return this.memberCount;
    }

    public Course getCourse(){
        return this.course;
    }

    public String getImageUrl(){
        return this.imageUrl;
    }
}
