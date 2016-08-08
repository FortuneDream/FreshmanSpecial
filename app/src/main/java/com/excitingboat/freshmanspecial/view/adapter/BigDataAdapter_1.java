package com.excitingboat.freshmanspecial.view.adapter;

import com.excitingboat.freshmanspecial.config.Data;
import com.excitingboat.yellowcake.ColorText;
import com.excitingboat.yellowcake.Yellowcake;

import java.util.Collections;

/**
 * Created by PinkD on 2016/8/9.
 * BigDataAdapter for Fragment1
 */
public class BigDataAdapter_1 extends BigDataAdapter{
    @Override
    public void setData(Yellowcake yellowcake, MyColorTextAdapter myColorTextAdapter, int position) {
        yellowcake.setData(Data.MAN_WOMAN[position], Data.COLORS);

        colorTexts.add(new ColorText(Data.COLORS[0], "男"));
        colorTexts.add(new ColorText(Data.COLORS[1], "女"));
        myColorTextAdapter.addAll(colorTexts);
    }
}
