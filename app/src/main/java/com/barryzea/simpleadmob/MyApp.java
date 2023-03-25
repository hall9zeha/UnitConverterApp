package com.barryzea.simpleadmob;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.PreferenceManager;

import dagger.hilt.android.HiltAndroidApp;

/****
 * SimpleAdMob
 * Created by Barry Zea H. on 25/3/23.
 * Copyright (c)  All rights reserved.
 ***/
@HiltAndroidApp
public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        SharedPreferences defaultPrefs= PreferenceManager.getDefaultSharedPreferences(this);
        if(defaultPrefs.getBoolean("darkTheme",false)){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

    }


}
