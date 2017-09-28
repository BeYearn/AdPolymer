package com.emagroup.adpolymer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.emagroup.openadsdk.OpenAdSdk;
import com.tapjoy.Tapjoy;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btSubmit;
    private Button btAddParam;
    private LayoutInflater layoutInflater;
    private LinearLayout llParamLayout;
    private EditText etEventName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OpenAdSdk.getInstance().activateActivity(this);

        btSubmit = (Button) findViewById(R.id.bt_submit);
        etEventName = (EditText) findViewById(R.id.et_event_name);
        llParamLayout = (LinearLayout) findViewById(R.id.ll_param_content);

        btSubmit.setOnClickListener(this);

        layoutInflater = LayoutInflater.from(this.getApplicationContext());

        addAParam();
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
            case R.id.bt_add_param:
                addAParam();
                break;

            case R.id.bt_submit:

                commitEvent();
                break;
        }
    }


    private void commitEvent() {
        HashMap<String, String> map = new HashMap<>();
        String eventName = etEventName.getText().toString();
        if (TextUtils.isEmpty(eventName)) {
            Toast.makeText(this, "请输入事件名称", Toast.LENGTH_LONG).show();
            return;
        }

        for (int i = 0; i < llParamLayout.getChildCount(); i++) {
            View childAt = llParamLayout.getChildAt(i);
            EditText etParamKey = (EditText) childAt.findViewById(R.id.et_param_key);
            EditText etParamValue = (EditText) childAt.findViewById(R.id.et_param_value);

            String paramKey = etParamKey.getText().toString();
            String paramValue = etParamValue.getText().toString();

            if (!TextUtils.isEmpty(paramKey)) {
                map.put(paramKey, paramValue);
            }
        }

        //HashMap<String, String> temp = new HashMap<>();
        //temp.put("account_name", "demoAccount");
        //OpenAdSdk.getInstance().adEvent(this,"ad_create_role", temp);
        OpenAdSdk.getInstance().adEvent(this,eventName, map);

    }


    private void addAParam() {
        if (null != btAddParam) {
            btAddParam.setVisibility(View.GONE);
        }

        View view = layoutInflater.inflate(R.layout.layout_param_content, null);

        btAddParam = (Button) view.findViewById(R.id.bt_add_param);

        btAddParam.setOnClickListener(this);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        llParamLayout.addView(view, layoutParams);

    }
}
