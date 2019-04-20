package com.badran.androidchallenge.data.api;

import com.badran.androidchallenge.data.models.GithubRepos;


import javax.inject.Inject;

import io.reactivex.Single;

public class DataRepository {
    private GithubService gitService;

    @Inject
    public DataRepository(GithubService gitService) {
        this.gitService = gitService;
    }

    public Single<GithubRepos> getTrendingRepos(String relativeDate, String sortBy, String orderBy) {
        return gitService.getRepos(relativeDate,sortBy,orderBy);
    }
}
