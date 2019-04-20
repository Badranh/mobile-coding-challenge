package com.badran.androidchallenge.base;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity extends DaggerAppCompatActivity {

    @LayoutRes
    protected abstract int LayoutRes();

    protected abstract void onCreateExtension(Bundle savedInstanceState);

    protected Unbinder unbinder;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LayoutRes());
        unbinder = ButterKnife.bind(this);
        onCreateExtension(savedInstanceState);
    }

}
