package com.excitingboat.freshmanspecial.config;

import android.content.Context;

import java.io.File;

/**
 * Created by PinkD on 2016/8/3.
 * Configurations
 */

public class Config {
    public static final boolean DEBUG = true;
    public static File cacheDir;
    public static final String BASE_URL = "http://hongyan.cqupt.edu.cn/cyxbsMobile/index.php/Home/WelcomeFreshman/";
    public static void init(Context context) {
        cacheDir = context.getExternalCacheDir();
    }

    public static final int KB = 1024;
    public static final int MB = 1024 * 1024;

    public static final int ERROR_NETWORK = 0;
    public static final int ERROR_INCORRECT = 1;

    public static final int INFORMATION_TYPE_TITLE_CONTENT = 0;
    public static final int INFORMATION_TYPE_TITLE_CONTENT_PICTURE = 1;
    public static final int INFORMATION_TYPE_PICTURE = 2;
    public static final int INFORMATION_TYPE_PERSONAL = 3;

}
