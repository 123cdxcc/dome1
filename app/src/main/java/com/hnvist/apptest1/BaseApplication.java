package com.hnvist.apptest1;

import android.app.Application;
import android.content.Context;

public class BaseApplication extends Application {

    public static  Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        BaseApplication.context = this;
    }

}
