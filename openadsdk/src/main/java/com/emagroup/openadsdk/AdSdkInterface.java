package com.emagroup.openadsdk;

import android.app.Activity;
import android.app.Application;

import java.util.HashMap;

/**
 * Created by beyearn on 2017/9/13.
 */

public interface AdSdkInterface {
    void activateApp(Application application);

    void adEvent(Activity activity,String event, HashMap<String, String> params);
}
