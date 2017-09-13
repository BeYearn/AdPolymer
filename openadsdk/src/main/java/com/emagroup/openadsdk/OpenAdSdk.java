package com.emagroup.openadsdk;

import android.app.Activity;
import android.app.Application;

import com.emagroup.openadsdk.impl.FacebookImpl;
import com.emagroup.openadsdk.impl.FirebaseImpl;

import java.util.HashMap;

/**
 * Created by beyearn on 2017/9/12.
 */

public class OpenAdSdk extends BaseSdk implements AdSdkInterface {
    private static OpenAdSdk mInstance;

    private OpenAdSdk() {
        super();  //父类的构造方法是无参数的，那么在子类中写不写都可以，不写的话会隐式地调用;父类的构造方法是带参数的且没有午餐构造的 必须显示调用
    }

    public static OpenAdSdk getInstance() {
        if (mInstance == null) {
            mInstance = new OpenAdSdk();
        }
        return mInstance;
    }


    public void activateApp(Application application) {
        if (isFacebook) {
            FacebookImpl faceBook = FacebookImpl.getInstance();
            faceBook.activateApp(application);
        }
        if (isFirebase) {

        }
        if (isAppsflyer) {

        }
        if (isTapjoy) {

        }
    }

    @Override
    public void adEvent(Activity activity,String event, HashMap<String, String> params) {
        if (isFacebook) {
            FacebookImpl faceBook = FacebookImpl.getInstance();
            faceBook.adEvent(activity,event,params);
        }
        if (isFirebase) {
            FirebaseImpl.getInstance().adEvent(activity,event,params);
        }
        if (isAppsflyer) {

        }
        if (isTapjoy) {

        }
    }


}
