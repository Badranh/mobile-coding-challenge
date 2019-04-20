package com.badran.androidchallenge.di.component;


import android.app.Application;

import com.badran.androidchallenge.base.BaseApplication;
import com.badran.androidchallenge.di.modules.ActivityBindingModule;
import com.badran.androidchallenge.di.modules.ApplicationModule;
import com.badran.androidchallenge.di.modules.ViewModelModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        ViewModelModule.class,
        ApplicationModule.class,
        ActivityBindingModule.class,
        AndroidSupportInjectionModule.class})
public interface AppComponent  extends AndroidInjector<BaseApplication> {


    @Component.Builder
    interface Builder{

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}

