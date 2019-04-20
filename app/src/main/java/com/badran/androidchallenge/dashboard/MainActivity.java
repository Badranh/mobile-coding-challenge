package com.badran.androidchallenge.dashboard;

import android.os.Bundle;

import com.badran.androidchallenge.R;
import com.badran.androidchallenge.base.BaseActivity;
import com.badran.androidchallenge.dashboard.fragmain.FragmentMain;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    @Inject
    FragmentMain fragmentMain;
    @Override
    protected int LayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreateExtension(Bundle savedInstanceState) {
        if(savedInstanceState==null)
             getSupportFragmentManager().beginTransaction().add(R.id.content,fragmentMain).commit();
    }

}
