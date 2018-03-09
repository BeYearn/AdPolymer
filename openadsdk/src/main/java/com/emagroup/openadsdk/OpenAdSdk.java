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
    private boolean isFirebase = false;
    private boolean isAppsflyer = false;
    private boolean isTapjoy = false;
    private boolean isFacebook = false;

    private OpenAdSdk() {
        try {

            // todo 使用openad_string.xml的方式来读,不用读文件了

            InputStream adConfStream = getClass().getResourceAsStream("/assets/openad.config");     //context.getAssets().open("test.properties"); 这种方法需要context
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(adConfStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.startsWith(";")) {
                    //用来判定需要接入哪几个渠道
                    if (line.equals("facebook")) {
                        isFacebook = true;
                    } else if (line.equals("firebase")) {
                        isFirebase = true;
                    } else if (line.equals("appsflyer")) {
                        isAppsflyer = true;
                    } else if (line.equals("tapjoy")) {
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
    public void onStart(Activity activity) {
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
    public void onStop(Activity activity) {
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

    /**
     * @param activity
     * @param event    事件名称
     * @param params   参数 可为null
     */
    @Override
    public void adEvent(Activity activity, HashMap<String, Boolean> channels, String event, HashMap<String, String> params) {
        Boolean haveFirebase = channels.get(AdConstants.FIREBASE);
        Boolean haveFacebook = channels.get(AdConstants.FACEBOOK);
        Boolean haveAppsflyer = channels.get(AdConstants.APPSFLYER);
        Boolean haveTapjoy = channels.get(AdConstants.TAPJOY);

        if (isFacebook && haveFacebook) {
            FacebookImpl faceBook = FacebookImpl.getInstance();
            faceBook.adEvent(activity, null, event, params);
        }
        if (isFirebase && haveFirebase) {
            FirebaseImpl.getInstance().adEvent(activity, null, event, params);
        }
        if (isAppsflyer && haveAppsflyer) {
            AppsflyerImpl.getInstance().adEvent(activity, null, event, params);
        }
        if (isTapjoy && haveTapjoy) {

        }
    }


}
