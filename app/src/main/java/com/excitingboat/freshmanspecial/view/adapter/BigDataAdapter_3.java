package com.excitingboat.freshmanspecial.view.adapter;

import com.excitingboat.freshmanspecial.config.Data;
import com.excitingboat.yellowcake.ColorText;
import com.excitingboat.yellowcake.Yellowcake;

/**
 * Created by PinkD on 2016/8/9.
 * BigDataAdapter for Fragment3
 */
public class BigDataAdapter_3 extends BigDataAdapter{

    @Override
    public void setData(Yellowcake yellowcake, MyColorTextAdapter myColorTextAdapter, int position) {
        yellowcake.setData(Data.JOB_PERCENT[position], Data.COLORS);
        int count = Data.JOB_TYPE.length;
        for (int i = 0; i < count; i++) {
            myColorTextAdapter.add(new ColorText(Data.COLORS[i], Data.JOB_TYPE[i]));
        }
        myColorTextAdapter.notifyDataSetChanged();
    }
}
