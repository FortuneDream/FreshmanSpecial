package com.excitingboat.freshmanspecial.view.adapter;

import com.excitingboat.freshmanspecial.config.Data;
import com.excitingboat.yellowcake.ColorText;
import com.excitingboat.yellowcake.Yellowcake;

/**
 * Created by PinkD on 2016/8/9.
 * * BigDataAdapter for Fragment2
 */
public class BigDataAdapter_2 extends BigDataAdapter {

    @Override
    public void setData(Yellowcake yellowcake, MyColorTextAdapter myColorTextAdapter, int position) {
        double[] numbers = new double[]{
                Data.HARD_SUBJECT_PERCENT[0][position],
                Data.HARD_SUBJECT_PERCENT[1][position],
                Data.HARD_SUBJECT_PERCENT[2][position]
        };

        yellowcake.setData(numbers, Data.COLORS);
        for (int i = 0; i < 3; i++) {
            myColorTextAdapter.add(new ColorText(Data.COLORS[i], Data.HARD_SUBJECT[i][position]));
        }
        myColorTextAdapter.notifyDataSetChanged();
    }
}
