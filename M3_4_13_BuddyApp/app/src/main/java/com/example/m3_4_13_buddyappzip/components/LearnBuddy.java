package com.example.m3_4_13_buddyappzip.components;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LearnBuddy implements Serializable {
    private String userId;
    private String username;
    private int buddyPoints;
    private BuddyRank buddyRank;
    private String description;
    private List<StudyProgram> studies;
    private String imageUrl;

    public LearnBuddy(String userId, String username, int buddyPoints, BuddyRank buddyRank, String description, String degree, String imageUrl){
        this.userId = userId;
        this.username = username;
        this.buddyPoints = buddyPoints;
        this.buddyRank = buddyRank;
        this.description = description;
        this.imageUrl = imageUrl;
        this.studies = new ArrayList<>();
    }

    public String getImage(){
        return this.imageUrl;
    }

    public String getUsername() {
        return this.username;
    }

    public int getBuddyPoints(){
        return this.buddyPoints;
    }

    public BuddyRank getBuddyRank(){
        return this.buddyRank;
    }

    public String getDescription(){
        return this.description;
    }

    public void setStudies(StudyProgram studyProgram){
        this.studies.add(studyProgram);
    }

    public List<StudyProgram> getStudies(){
        return this.studies;
    }
}
