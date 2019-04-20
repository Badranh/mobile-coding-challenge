package com.badran.androidchallenge.di.submodules;

import com.badran.androidchallenge.dashboard.fragmain.ContractMain;
import com.badran.androidchallenge.dashboard.fragmain.FragmentMain;
import com.badran.androidchallenge.dashboard.fragmain.PresenterMain;
import com.badran.androidchallenge.di.annotations.ActivityScoped;
import com.badran.androidchallenge.di.annotations.FragmentScoped;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainSubModules {

    @ActivityScoped
    @Binds
    abstract ContractMain.Presenter
    MainPresenter(PresenterMain presenter);

    @FragmentScoped
    @ContributesAndroidInjector
    abstract FragmentMain fragmentMain();
}
