package com.excitingboat.freshmanspecial.view.adapter;

import android.util.Log;

import com.excitingboat.freshmanspecial.config.Data;
import com.excitingboat.yellowcake.ColorText;
import com.excitingboat.yellowcake.Yellowcake;

import java.util.Collections;

/**
 * Created by PinkD on 2016/8/9.
 * BigDataAdapter for Fragment1
 */
public class BigDataAdapter_1 extends BigDataAdapter{
    private int schoolPosition;

    @Override
    protected void init() {
        school.clear();
        Collections.addAll(school, Data.SCHOOL_1);
    }

    public int getSchoolPosition() {
        return schoolPosition;
    }

    public void setSchoolPosition(int schoolPosition) {
        this.schoolPosition = schoolPosition;
    }

    @Override
    public void setData(Yellowcake yellowcake, MyColorTextAdapter myColorTextAdapter, int position) {
        yellowcake.setData(Data.MAN_WOMAN[schoolPosition][position], Data.COLORS);
        colorTexts.add(new ColorText(Data.COLORS[0], "男"));
        colorTexts.add(new ColorText(Data.COLORS[1], "女"));
        //也不知道是怎么回事，，，反正就是不能添加。。只能set了。。。
        myColorTextAdapter.setColorTexts(colorTexts);
//        myColorTextAdapter.addAll(colorTexts);
    }
}
