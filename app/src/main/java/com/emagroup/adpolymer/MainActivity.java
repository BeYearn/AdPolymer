package com.emagroup.adpolymer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.emagroup.openadsdk.OpenAdSdk;
import com.tapjoy.Tapjoy;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btCreatCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OpenAdSdk.getInstance().activateActivity(this);

        btCreatCount = (Button) findViewById(R.id.bt_creat_account);


        btCreatCount.setOnClickListener(this);


        /*AppEventsLogger.activateApp(this.getApplication(),"appid");

        AppEventsLogger logger = AppEventsLogger.newLogger(this);
        Bundle parameters = new Bundle();
        parameters.putString(AppEventsConstants.EVENT_PARAM_CURRENCY, "USD");
        parameters.putString(AppEventsConstants.EVENT_PARAM_CONTENT_TYPE, "product");
        parameters.putString(AppEventsConstants.EVENT_PARAM_CONTENT_ID, "HDFU-8452");

        logger.logEvent(AppEventsConstants.EVENT_NAME_ADDED_TO_CART,
                parameters);*/


        /*FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "1123");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "1123hha");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);*/


        /*AppsFlyerConversionListener conversionDataListener = new AppsFlyerConversionListener() {
            @Override
            public void onInstallConversionDataLoaded(Map<String, String> map) {
                Log.e("1","1");
            }

            @Override
            public void onInstallConversionFailure(String s) {
                Log.e("2","2");
            }

            @Override
            public void onAppOpenAttribution(Map<String, String> map) {
                Log.e("3","3");
            }

            @Override
            public void onAttributionFailure(String s) {
                Log.e("4","4");
            }
        };
        AppsFlyerLib.getInstance().init("appflyer key", conversionDataListener);
        AppsFlyerLib.getInstance().startTracking(this.getApplication());

        Map<String, Object> eventValue = new HashMap<>();
        eventValue.put(AFInAppEventParameterName.CURRENCY,9);
        eventValue.put(AFInAppEventParameterName.SCORE,100);
        AppsFlyerLib.getInstance().trackEvent(this, AFInAppEventType.LEVEL_ACHIEVED,eventValue);
        String appsFlyerId = AppsFlyerLib.getInstance().getAppsFlyerUID(this);*/


        Tapjoy.connect(this.getApplicationContext(), "d-XDJk7ORiSARxySkrUGIwEC6PHleqX9FxW7Qzmde3rMfUV-BKKD4CF7eC78");

    }

    @Override
    protected void onStart() {
        super.onStart();
        OpenAdSdk.getInstance().onStart(this);
        //Tapjoy.onActivityStart(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        OpenAdSdk.getInstance().onStop(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_creat_account:

                HashMap<String, String> map = new HashMap<>();
                map.put("account_name", "demoAccount");
                OpenAdSdk.getInstance().adEvent(this,"ad_create_role", map);

                break;
        }
    }
}
