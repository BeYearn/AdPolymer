package com.emagroup.openadsdk;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * sdcard的存在于上下文无关
 *
 * @author beyearn
 */
public class Utils {

    /**
     * 用于读取sdcard的数据
     *
     * @param path     路径
     * @param fileName 文件名
     * @return 读出的字符内容
     */
    public static String readFromSdcard(String path, String fileName) {

        String state = Environment.getExternalStorageState();
        File rooFile = Environment.getExternalStorageDirectory();
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            try {
                File file = new File(rooFile + path + fileName);
                FileReader fileReader = new FileReader(file);
                bufferedReader = new BufferedReader(fileReader);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line + "\r\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        return stringBuilder.toString();
    }


    /**
     * 获取清单文件中 meta-data的值
     *
     * @param context
     * @param metaDataName
     * @return
     */
    public static String getMetaDataValue(Context context, String metaDataName) {
        PackageManager pm = context.getPackageManager();
        ApplicationInfo appinfo;
        String metaDataValue = "";
        try {
            appinfo = pm.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            Bundle metaData = appinfo.metaData;
            metaDataValue = metaData.getString(metaDataName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return metaDataValue;
    }


    /**
     * 向sdcard下自定义目录存放文件
     */
    public boolean saveFileToSdcardDir(String path, String fileName, byte[] data) {
        boolean flag = false;
        String state = Environment.getExternalStorageState();
        FileOutputStream outputStream = null;
        File rootFile = Environment.getExternalStorageDirectory(); // 获得sdcard的根路径
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            File file = new File(rootFile + path);
            if (!file.exists()) {
                file.mkdirs();
            }
            try {
                outputStream = new FileOutputStream(new File(file, fileName));
                outputStream.write(data, 0, data.length);
                flag = true;
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return flag;
    }


    /**
     * 删除一个文件
     *
     * @param path     路径
     * @param fileName 文件名
     */
    public boolean deleteFileFromSdcard(String path, String fileName) {
        boolean flag = false;
        File rooFile = Environment.getExternalStorageDirectory();
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File exitFile = new File(rooFile + path, fileName);
            if (exitFile.exists()) {
                flag = exitFile.delete();
            }
        }
        return flag;
    }

}