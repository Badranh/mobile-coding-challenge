package com.badran.androidchallenge.data.models;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public class GithubRepo {
    @SerializedName("name")
    private String repoName;
    @SerializedName("description")
    private String repoDesc;
    @SerializedName("open_issues")
    private int openIssues;
    @SerializedName("stargazers_count")
    private int starCount;
    @SerializedName("language")
    private String languageUsed;
    @SerializedName("owner")
    private GithubRepoOwner owner;

    public GithubRepo(String repoName, String repoDesc, int openIssues, int starCount, String languageUsed, GithubRepoOwner owner) {
        this.repoName = repoName;
        this.repoDesc = repoDesc;
        this.openIssues = openIssues;
        this.starCount = starCount;
        this.languageUsed = languageUsed;
        this.owner = owner;
    }
}
