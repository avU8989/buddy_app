package com.example.m3_4_13_buddyappzip.components;

public class Group {
    private String groupname;
    private String avatarUrl;

    public Group(String groupname, String avatarUrl) {
        this.groupname = groupname;
        this.avatarUrl = avatarUrl;
    }

    public String getGroupname() {
        return groupname;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }
}
