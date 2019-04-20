package com.badran.androidchallenge.data.models;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public class GithubRepoOwner {

    @SerializedName("login")
    private String username;

    @SerializedName("avatar_url")
    private String avatarUrl;

    @SerializedName("html_url")
    private String githubLink;

    public GithubRepoOwner(String avatarUrl, String githubLink) {
        this.avatarUrl = avatarUrl;
        this.githubLink = githubLink;
    }
}
