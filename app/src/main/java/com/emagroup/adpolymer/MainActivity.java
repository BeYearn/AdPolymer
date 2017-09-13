package com.emagroup.adpolymer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.emagroup.openadsdk.OpenAdSdk;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btCreatCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btCreatCount = (Button) findViewById(R.id.bt_creat_account);


        btCreatCount.setOnClickListener(this);


        /*AppEventsLogger logger = AppEventsLogger.newLogger(this);
        Bundle parameters = new Bundle();
        parameters.putString(AppEventsConstants.EVENT_PARAM_CURRENCY, "USD");
        parameters.putString(AppEventsConstants.EVENT_PARAM_CONTENT_TYPE, "product");
        parameters.putString(AppEventsConstants.EVENT_PARAM_CONTENT_ID, "HDFU-8452");

        logger.logEvent(AppEventsConstants.EVENT_NAME_ADDED_TO_CART,
                parameters);*/


        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "1123");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "1123hha");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

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
