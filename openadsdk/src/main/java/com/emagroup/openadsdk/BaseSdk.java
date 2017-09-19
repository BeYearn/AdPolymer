package com.emagroup.openadsdk;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by beyearn on 2017/9/12.
 */

public abstract class BaseSdk implements AdSdkInterface {

    public String facebookId;
    public String appsflyerId;
    public String tapjoyId;

    public BaseSdk() {
        try {
            InputStream adConfStream = getClass().getResourceAsStream("/assets/openad.config");     //context.getAssets().open("test.properties"); 这种方法需要context
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(adConfStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.startsWith(";")) {
                    //用来获取渠道参数
                    if(line.contains("facebookAppId=")){
                        String[] split = line.split("facebookAppId=");              //会分成""和后面的id
                        facebookId = split[1].trim();
                    }else if(line.contains("appsflyerAppId=")){
                        String[] split = line.split("appsflyerAppId=");
                        appsflyerId = split[1].trim();
                    }else if(line.contains("tapjoyAppId=")){
                        String[] split = line.split("tapjoyAppId=");
                        tapjoyId = split[1].trim();
                    }

                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
