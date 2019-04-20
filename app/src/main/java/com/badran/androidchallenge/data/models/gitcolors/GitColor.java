package com.badran.androidchallenge.data.models.gitcolors;


import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public class GitColor {

    public GitColor(String color) {
        this.color = color;
    }

    @SerializedName("color")
    private String color;
}
