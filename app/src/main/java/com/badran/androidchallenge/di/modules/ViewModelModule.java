package com.badran.androidchallenge.di.modules;


import com.badran.androidchallenge.dashboard.fragmain.ViewModelMain;
import com.badran.androidchallenge.di.annotations.ViewModelKey;
import com.badran.androidchallenge.factories.ViewModelFactory;

import javax.inject.Singleton;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule  {
    @Singleton
    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelMain.class)
    abstract ViewModel bindMainViewModel(ViewModelMain viewModelMain);
}
