package com.emagroup.adpolymer;

import android.app.Application;

import com.facebook.appevents.AppEventsLogger;

/**
 * Created by beyearn on 2017/9/12.
 */

public class AdApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppEventsLogger.activateApp(this);
    }

}
