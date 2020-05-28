package com.leo.impro;

import android.app.Application;
import android.content.Context;

/**
 * Created by leo
 * on 2020/5/28.
 */
public class MyApplication extends Application {
    private static MyApplication context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext() {
        return context;
    }
}
