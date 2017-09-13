package com.emagroup.adpolymer;

import android.app.Application;

import com.emagroup.openadsdk.OpenAdSdk;

/**
 * Created by beyearn on 2017/9/12.
 */

public class AdApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        OpenAdSdk.getInstance().activateApp(this);
    }

}
