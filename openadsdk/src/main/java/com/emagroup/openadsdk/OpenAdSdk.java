package com.emagroup.openadsdk;

import android.app.Application;
import android.util.Log;

/**
 * Created by beyearn on 2017/9/12.
 */

public class OpenAdSdk extends BaseSdk {
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

            try {
                Class<?> aClass = Class.forName("com.emagroup.openadsdk.impl.FaceBookImpl");
                Log.e("hahh", aClass.getName());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
        if (isFirebase) {

        }
        if (isAppsflyer) {

        }
        if (isTapjoy) {

        }
    }


}
