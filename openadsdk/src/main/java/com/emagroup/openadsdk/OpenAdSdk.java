package com.emagroup.openadsdk;

import android.app.Activity;
import android.app.Application;

import com.emagroup.openadsdk.impl.AppsflyerImpl;
import com.emagroup.openadsdk.impl.FacebookImpl;
import com.emagroup.openadsdk.impl.FirebaseImpl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Created by beyearn on 2017/9/12.
 */

public class OpenAdSdk implements AdSdkInterface {
    private static OpenAdSdk mInstance;
    private boolean isFirebase;
    private boolean isAppsflyer;
    private boolean isTapjoy;
    private boolean isFacebook;

    private OpenAdSdk() {
        try {
            InputStream adConfStream = getClass().getResourceAsStream("/assets/openad.config");     //context.getAssets().open("test.properties"); 这种方法需要context
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(adConfStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.startsWith(";")) {
                    //用来判定需要接入哪几个渠道
                    if (line.contains("facebook")) {
                        isFacebook = true;
                    } else if (line.contains("firebase")) {
                        isFirebase = true;
                    } else if (line.contains("appsflyer")) {
                        isAppsflyer = true;
                    } else if (line.contains("tapjoy")) {
                        isTapjoy = true;
                    }
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static OpenAdSdk getInstance() {
        if (mInstance == null) {
            mInstance = new OpenAdSdk();
        }
        return mInstance;
    }


    public void activateApplication(Application application) {
        if (isFacebook) {
            FacebookImpl faceBook = FacebookImpl.getInstance();
            faceBook.activateApplication(application);
        }
        if (isFirebase) {
            //不需用
        }
        if (isAppsflyer) {
            AppsflyerImpl appsflyer = AppsflyerImpl.getInstance();
            appsflyer.activateApplication(application);
        }
        if (isTapjoy) {
            //不需用
        }
    }

    @Override
    public void activateActivity(Activity activity) {
        if (isFacebook) {
            //不需用
        }
        if (isFirebase) {
            //不需用
        }
        if (isAppsflyer) {
            //不需用
        }
        if (isTapjoy) {

        }
    }

    @Override
    public void onStart() {
        if (isFacebook) {
            //不需用
        }
        if (isFirebase) {
            //不需用
        }
        if (isAppsflyer) {
            //不需用
        }
        if (isTapjoy) {

        }
    }

    @Override
    public void onStop() {
        if (isFacebook) {
            //不需用
        }
        if (isFirebase) {
            //不需用
        }
        if (isAppsflyer) {
            //不需用
        }
        if (isTapjoy) {

        }
    }

    @Override
    public void adEvent(Activity activity, String event, HashMap<String, String> params) {
        if (isFacebook) {
            FacebookImpl faceBook = FacebookImpl.getInstance();
            faceBook.adEvent(activity, event, params);
        }
        if (isFirebase) {
            FirebaseImpl.getInstance().adEvent(activity, event, params);
        }
        if (isAppsflyer) {
            AppsflyerImpl.getInstance().adEvent(activity, event, params);
        }
        if (isTapjoy) {

        }
    }


}
