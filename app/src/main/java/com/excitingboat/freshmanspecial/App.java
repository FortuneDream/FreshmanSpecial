package com.excitingboat.freshmanspecial;

import android.app.Application;

import com.excitingboat.freshmanspecial.config.Config;

/**
 * Created by PinkD on 2016/8/3.
 * Application
 */
public class App extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Config.init(this);
    }
}
