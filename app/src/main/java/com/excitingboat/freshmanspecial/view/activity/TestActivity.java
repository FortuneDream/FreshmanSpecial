package com.excitingboat.freshmanspecial.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.excitingboat.freshmanspecial.R;
import com.excitingboat.freshmanspecial.view.adapter.MyColorTextAdapter;
import com.excitingboat.yellowcake.ColorText;
import com.excitingboat.yellowcake.ColorTextListView;
import com.excitingboat.yellowcake.Yellowcake;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    private List<ColorText> colorTexts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ColorTextListView colorTextListView = (ColorTextListView) findViewById(R.id.list);
        Yellowcake yellowcake = (Yellowcake) findViewById(R.id.test_cake);
        yellowcake.setData(new double[]{1, 88, 9}, new int[]{0xFF66CCFF, 0xFFEE82EE, 0xFF9999FF});
        MyColorTextAdapter myColorTextAdapter = new MyColorTextAdapter();
        colorTexts = new ArrayList<>();
        colorTexts.add(new ColorText(0xFF66CCFF, "天依"));
        colorTexts.add(new ColorText(0xFFEE82EE, "心华"));
//        colorTexts.add(new ColorText(0xFFFF0000, "阿绫"));
//        colorTexts.add(new ColorText(0xFF9999FF, "星尘"));
        colorTexts.add(new ColorText(0xFFFC7AFC, "PinkD"));

        myColorTextAdapter.addAll(colorTexts);
        colorTextListView.setMax(2);
        colorTextListView.setAdapter(myColorTextAdapter);
    }
}
