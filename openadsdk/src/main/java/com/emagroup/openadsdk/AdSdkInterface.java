package com.emagroup.openadsdk;

import android.app.Activity;
import android.app.Application;

import java.util.HashMap;

/**
 * Created by beyearn on 2017/9/13.
 */

public interface AdSdkInterface {
    /**
     * 在application 的 oncreate中调用
     *
     * @param application
     */
    void activateApplication(Application application);


    /**
     * 在activity 的 oncreate 中调用
     * @param activity
     */
    void activateActivity(Activity activity);



    void onStart();


    void onStop();


    /**
     * 增加事件
     *
     * @param activity
     * @param event
     * @param params
     */
    void adEvent(Activity activity, String event, HashMap<String, String> params);
}
