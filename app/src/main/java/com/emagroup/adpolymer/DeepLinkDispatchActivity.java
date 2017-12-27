package com.emagroup.adpolymer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by beyearn on 2017/10/10.
 */

public class DeepLinkDispatchActivity extends AppCompatActivity {


    /*
     真实坑爹啊这个方法
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        setContentView(R.layout.activity_main2);
        Log.e("oncreat","deeplink");
        dispatch();
    }*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch);
        Log.e("oncreat","deeplink");
        dispatch();
    }

    private void dispatch() {
        Intent intent = getIntent();
        String action = intent.getAction();
        Uri data = intent.getData();

        Log.e("aciton", action);
        Log.e("data", data.toString());

    }

    /**
     * 配合singleTask使用 再启动时就不再走oncreate而是走这个
     * 当调用到onNewIntent(intent)的时候，需要在onNewIntent() 中使用setIntent(intent)赋值给Activity的Intent.否则，后续的getIntent()都是得到老的Intent
     *
     * @param intent
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }
}
