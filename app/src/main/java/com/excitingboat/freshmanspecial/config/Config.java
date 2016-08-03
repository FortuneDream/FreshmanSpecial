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

    public static void init(Context context) {
        cacheDir = context.getExternalCacheDir();
    }

    public static final int KB = 1024;
    public static final int MB = 1024 * 1024;

}
