package com.badran.androidchallenge.dashboard.fragmain;

import com.badran.androidchallenge.data.models.GithubRepo;
import com.badran.androidchallenge.di.annotations.ActivityScoped;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

//This Class maintains the view state only.

@Singleton
public class ViewModelMain extends ViewModel {

    private final MutableLiveData<List<GithubRepo>> repos = new MutableLiveData<>();
    private final MutableLiveData<Boolean> gotAnError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private final MutableLiveData<Boolean> successfullyLoaded = new MutableLiveData<>();

    @Inject
    public ViewModelMain() {
        successfullyLoaded.setValue(false);
    }

    //to be access from View, to preserve the encapsulation

    public LiveData<Boolean> getSuccessfullyLoaded() {
        return successfullyLoaded;
    }

    public LiveData<List<GithubRepo>> getRepos() {
        return repos;
    }

    public LiveData<Boolean> getGotAnError() {
        return gotAnError;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    //to be accessed from presenter only
    public void setReposData(List<GithubRepo> data){
        repos.setValue(data);
    }
    public void setGotAnError(boolean data){
        gotAnError.setValue(data);
    }
    public void setIsLoading(boolean data){
        isLoading.setValue(data);
    }
    public void setSuccessfullyLoaded(boolean data){
        successfullyLoaded.setValue(data);
    }
}
