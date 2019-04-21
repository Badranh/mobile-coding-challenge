package com.badran.androidchallenge.dashboard.fragmain.adapters;

public interface RepoViewHolder {
    void setDataToTextView(String repoName, String repoDesc, String repoLanguage, String repoOwner, String repoStarCount);
    void setImageToAvatar(String url);
    void setLanguageColor(String Hex);
}
