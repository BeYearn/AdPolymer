package com.emagroup.openadsdk.impl;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.emagroup.openadsdk.BaseSdk;

import java.util.HashMap;

/**
 * Created by beyearn on 2017/9/19.
 */

public class TapjoyImpl extends BaseSdk {


    private static TapjoyImpl mInstance;

    ////父类的构造方法是无参数的，那么在子类中写不写都可以，不写的话会隐式地调用;父类的构造方法是带参数的且没有午餐构造的 必须显示调用
    public static TapjoyImpl getInstance() {
        if (mInstance == null) {
            mInstance = new TapjoyImpl();
        }
        return mInstance;
    }

    @Override
    public void activateApplication(Application application) {

    }

    @Override
    public void activateActivity(Activity activity) {
        try {
            Class<?> aClass = Class.forName("com.tapjoy.Tapjoy");
            aClass.getMethod("connect", Context.class, String.class).invoke(null, activity.getApplicationContext(), tapjoyId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStart(Activity activity) {
        try {
            Class<?> aClass = Class.forName("com.tapjoy.Tapjoy");
            aClass.getMethod("onActivityStart", Activity.class).invoke(null, activity);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onStop(Activity activity) {
        try {
            Class<?> aClass = Class.forName("com.tapjoy.Tapjoy");
            aClass.getMethod("onActivityStop", Activity.class).invoke(null, activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void adEvent(Activity activity, HashMap<String, Boolean> channels, String event, HashMap<String, String> params) {

    }
}
