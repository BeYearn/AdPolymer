package com.emagroup.openadsdk;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by beyearn on 2017/9/12.
 */

public class BaseSdk {

    public boolean isFirebase = false;
    public boolean isTapjoy = false;
    public boolean isAppsflyer = false;
    public boolean isFacebook = false;

    BaseSdk() {
        try {
            InputStream adConfStream = getClass().getResourceAsStream("/assets/openad.properties");     //context.getAssets().open("test.properties"); 这种方法需要context
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(adConfStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.startsWith(";")) {

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
}
