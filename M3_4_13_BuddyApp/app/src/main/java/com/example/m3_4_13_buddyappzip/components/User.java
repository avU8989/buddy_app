package com.example.m3_4_13_buddyappzip.components;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String avatarUrl;

    public User(String username){
        this.username = username;
    }
    public User(String username, String avatarUrl) {
        this.username = username;
        this.avatarUrl = avatarUrl;
    }

    public String getUsername() {
        return username;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }
}
