package com.badran.androidchallenge.base;

import com.badran.androidchallenge.di.component.AppComponent;
import com.badran.androidchallenge.di.component.DaggerAppComponent;


import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class BaseApplication extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent component = DaggerAppComponent.builder().application(this).build();
        component.inject(this);
        return component;
    }

}
