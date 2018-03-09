package com.emagroup.openadsdk.impl;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.emagroup.openadsdk.BaseSdk;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by beyearn on 2017/9/13.
 */

public class FirebaseImpl extends BaseSdk {

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
    public void onStart(Activity activity) {

    }

    @Override
    public void onStop(Activity activity) {

    }

    @Override
    public void adEvent(Activity activity, HashMap<String, Boolean> channels, @NonNull String event, HashMap<String, String> params) {
        try {
            Class<?> aClass = Class.forName("com.google.firebase.analytics.FirebaseAnalytics");
            Object instance = aClass.getMethod("getInstance", Context.class).invoke(null, activity);

            Bundle bundle = new Bundle();
            if (params != null) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    bundle.putString(entry.getKey(), entry.getValue());
                }
            }

            aClass.getMethod("logEvent", String.class, Bundle.class).invoke(instance, event, bundle);    // firebase的参数bundle并非可选参数

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
