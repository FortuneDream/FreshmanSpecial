package com.excitingboat.yellowcake;

import android.content.Context;

import java.text.DecimalFormat;

/**
 * Created by PinkD on 2016/8/5.
 * px/dp/sp Utils
 */
public class Utils {

    public static int dp2px(Context context, float dpValue) {
        return (int) (dpValue * context.getResources().getDisplayMetrics().density + 0.5f);
    }

    public static int px2dp(Context context, float pxValue) {
        return (int) (pxValue / context.getResources().getDisplayMetrics().density + 0.5f);
    }


    public static int px2sp(Context context, float pxValue) {
        return (int) (pxValue / context.getResources().getDisplayMetrics().scaledDensity + 0.5f);
    }


    public static int sp2px(Context context, float spValue) {
        return (int) (spValue * context.getResources().getDisplayMetrics().scaledDensity + 0.5f);
    }

    public static String formatNumber(double number) {
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        return decimalFormat.format(number);
    }
}
