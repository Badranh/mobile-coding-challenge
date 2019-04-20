package com.badran.androidchallenge.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class GithubRepos {
    @SerializedName("items")
    private List<GithubRepo> githubRepo = new ArrayList<>();
}
