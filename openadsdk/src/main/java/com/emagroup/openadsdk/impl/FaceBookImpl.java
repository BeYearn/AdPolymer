package com.emagroup.openadsdk.impl;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.emagroup.openadsdk.AdSdkInterface;

import java.util.HashMap;

/**
 * Created by beyearn on 2017/9/12.
 */

public class FacebookImpl implements AdSdkInterface {

    private static FacebookImpl mInstance;

    public static FacebookImpl getInstance() {
        if (mInstance == null) {
            mInstance = new FacebookImpl();
        }
        return mInstance;
    }

    /**
     * facebook 在application 的oncreat中调用
     *
     * @param application
     */
    @Override
    public void activateApp(Application application) {
        try {
            Class<?> aClass = Class.forName("com.facebook.appevents.AppEventsLogger");
            aClass.getMethod("activateApp", Application.class).invoke(null, application);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 在事件发生处调用
     * @param activity
     * @param event
     * @param params
     */
    @Override
    public void adEvent(Activity activity, String event, HashMap<String, String> params) {
        try {
            Class<?> aClass = Class.forName("com.facebook.appevents.AppEventsLogger");
            Object logger = aClass.getMethod("newLogger", Context.class).invoke(null, activity);

            if (params != null) {
                Bundle bundle = new Bundle();
                bundle.putString("account_name", params.get("account_name"));
                aClass.getMethod("logEvent", String.class, Bundle.class).invoke(logger, event, bundle);
            } else {
                aClass.getMethod("logEvent", String.class).invoke(logger, event);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
