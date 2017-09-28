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
 * Created by beyearn on 2017/9/12.
 */

public class FacebookImpl extends BaseSdk {

    private static FacebookImpl mInstance;

    ////父类的构造方法是无参数的，那么在子类中写不写都可以，不写的话会隐式地调用;父类的构造方法是带参数的且没有午餐构造的 必须显示调用
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
    public void activateApplication(Application application) {
        try {
            //Class<?> bClass = Class.forName("com.facebook.FacebookSdk");
            //bClass.getMethod("setApplicationId",String.class).invoke(null,facebookId);  //这样也不行 因为神奇的启动方式 A valid Facebook app id must be set in the AndroidManifest.xml or set by calling FacebookSdk.setApplicationId before initializing the sdk.

            Class<?> aClass = Class.forName("com.facebook.appevents.AppEventsLogger");
            aClass.getMethod("activateApp", Application.class).invoke(null, application);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    /**
     * 在事件发生处调用
     *
     * @param activity
     * @param event
     * @param params
     */
    @Override
    public void adEvent(Activity activity, @NonNull String event, HashMap<String, String> params) {
        try {
            Class<?> aClass = Class.forName("com.facebook.appevents.AppEventsLogger");
            Object logger = aClass.getMethod("newLogger", Context.class).invoke(null, activity);

            if (params != null) {
                Bundle bundle = new Bundle();
                for (Map.Entry<String,String> entry :params.entrySet()){
                    bundle.putString(entry.getKey(), entry.getValue());
                }
                aClass.getMethod("logEvent", String.class, Bundle.class).invoke(logger, event, bundle);
            } else {
                aClass.getMethod("logEvent", String.class).invoke(logger, event);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
