package com.badran.androidchallenge.di.modules;

import com.badran.androidchallenge.dashboard.MainActivity;
import com.badran.androidchallenge.di.annotations.ActivityScoped;
import com.badran.androidchallenge.di.submodules.MainSubModules;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = MainSubModules.class)
    abstract MainActivity bindMainActivity();

}
