package com.emagroup.openadsdk.impl;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.emagroup.openadsdk.BaseSdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

/**
 * Created by beyearn on 2017/9/14.
 */

public class AppsflyerImpl extends BaseSdk {


    private static AppsflyerImpl mInstance;


    public static AppsflyerImpl getInstance() {
        if (mInstance == null) {
            mInstance = new AppsflyerImpl();
        }
        return mInstance;
    }

    @Override
    public void activateApplication(Application application) {
        try {

            Class<?> classListener = Class.forName("com.appsflyer.AppsFlyerConversionListener");

            Class<?> classAppsFlyerLib = Class.forName("com.appsflyer.AppsFlyerLib");
            Object instance = classAppsFlyerLib.getMethod("getInstance").invoke(null);

            Object listener = Proxy.newProxyInstance(AppsflyerImpl.class.getClassLoader(),
                    new Class[]{classListener}, new AfListenerImpl());

            classAppsFlyerLib.getMethod("init", String.class, classListener).invoke(instance, appsflyerId, listener);

            //就绪后开始track
            classAppsFlyerLib.getMethod("startTracking", Application.class).invoke(instance, application);
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
     * @param activity
     * @param eventName 限制45个字符
     * @param params    如果事件需要统计收益(revenue), 那么请务必使用af_revenue作为指定的key(其余可选/可自定义)
     *                  <p>
     *                  **遍历中不可增,删的话得用map.entrySet().iterator()的it的remove,改的话均可
     */
    @Override
    public void adEvent(Activity activity, HashMap<String, Boolean> channels, @NonNull String eventName, HashMap<String, String> params) {
        try {
            HashMap<String, String> newParams = new HashMap<>();
            if (params != null) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    if (entry.getKey().equals("////////预定义的支付金额的字段////////")) {
                        newParams.put("af_revenue", entry.getValue());
                    } else {
                        newParams.put(entry.getKey(), entry.getValue());
                        Log.e("appsflyerAD:",entry.getKey()+" : "+entry.getValue());
                    }
                }
            }

            Class<?> aClass = Class.forName("com.appsflyer.AppsFlyerLib");
            Object instance = aClass.getMethod("getInstance").invoke(null);
            aClass.getMethod("trackEvent", Context.class, String.class, Map.class).invoke(instance, activity.getApplicationContext(), eventName, newParams);

            /*Map<String, Object> eventValue = new HashMap<String, Object>();
            eventValue.put("level",9);
            eventValue.put("score",100);
            AppsFlyerLib.getInstance().trackEvent(activity.getApplicationContext(),"level_bbb",eventValue);*/
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    //----------------------------------------------------------------------------------------------
    public class AfListenerImpl implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            try {
                Log.d(TAG, "invoke, method: " + method.getName());

                switch (method.getName()) {
                    case "onInstallConversionDataLoaded":

                        Log.e("1", "1");
                        break;
                    case "onInstallConversionFailure":
                        Log.e("2", "2");
                        break;
                    case "onAppOpenAttribution":
                        Log.e("3", "3");
                        break;
                    case "onAttributionFailure":
                        Log.e("4", "4");
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return proxy;
        }
    }


}
