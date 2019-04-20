package com.badran.androidchallenge.data.api;

import com.badran.androidchallenge.data.models.GithubRepo;
import com.badran.androidchallenge.data.models.GithubRepos;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GithubService {

    @GET("search/repositories")
    Single<GithubRepos> getRepos(@Query("q") String date, @Query("sort") String sortingBy, @Query("order") String orderBy);
}
