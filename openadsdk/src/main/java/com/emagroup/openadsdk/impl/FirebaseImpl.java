package com.emagroup.openadsdk.impl;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.emagroup.openadsdk.AdSdkInterface;

import java.util.HashMap;

/**
 * Created by beyearn on 2017/9/13.
 */

public class FirebaseImpl implements AdSdkInterface {

    private static FirebaseImpl mInstance;

    public static FirebaseImpl getInstance() {
        if (mInstance == null) {
            mInstance = new FirebaseImpl();
        }
        return mInstance;
    }

    @Override
    public void activateApplication(Application application) {
        //firebase 的分析 无需在application中初始化
    }

    @Override
    public void activateActivity(Activity activity) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void adEvent(Activity activity, String event, HashMap<String, String> params) {
        try {
            Class<?> aClass = Class.forName("com.google.firebase.analytics.FirebaseAnalytics");
            Object instance = aClass.getMethod("getInstance", Context.class).invoke(null, activity);

            Bundle bundle = new Bundle();
            bundle.putString("account_name", params.get("account_name"));
            aClass.getMethod("logEvent", String.class, Bundle.class).invoke(instance, event, bundle);    // firebase的参数bundle并非可选参数

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
