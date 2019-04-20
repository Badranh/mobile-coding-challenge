package com.badran.androidchallenge.factories;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dagger.Provides;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final Map<Class<? extends ViewModel>, Provider<ViewModel>> instances;

    @Inject
    public ViewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> instances) {
        this.instances = instances;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        Provider<? extends ViewModel> instance = instances.get(modelClass);
        if(instance == null){
            for(Map.Entry<Class<? extends ViewModel>, Provider<ViewModel>> entry : instances.entrySet()){
                if(modelClass.isAssignableFrom(entry.getKey())){
                    instance = entry.getValue();
                    break;
                }
            }
        }
        //Sorry but I'm obligated
        if(instance == null){
            throw new IllegalArgumentException("Unknown Model Class " + modelClass);
        }
        try {
            return (T) instance.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
