package com.excitingboat.freshmanspecial.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.excitingboat.freshmanspecial.R;
import com.excitingboat.freshmanspecial.utils.DensityUtils;

public class FreshmanMainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freshman_main);
        init();
        Log.d("danwei", "onCreate: "+"dp:"+ DensityUtils.pxTodp(FreshmanMainActivity.this,169));
    }

    private void init() {
        findViewById(R.id.bt_toolbar_back).setOnClickListener(this);
        findViewById(R.id.freshman_guide_card).setOnClickListener(this);
        findViewById(R.id.freshman_big_data_card).setOnClickListener(this);
        findViewById(R.id.freshman_cqupt_card).setOnClickListener(this);
        title = (TextView) findViewById(R.id.tv_toolbar_title);
        title.setText("2016年新生专题网");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.freshman_guide_card:
                startActivity(new Intent(this, FreshmenGuideActivity.class));
                break;
            case R.id.freshman_big_data_card:
                startActivity(new Intent(this, FreshmenBigDataActivity.class));
//                startActivity(new Intent(this, TestActivity.class));
                break;
            case R.id.freshman_cqupt_card:
                startActivity(new Intent(this, FreshmenCQUPTStyleActivity.class));
                break;
            case R.id.bt_toolbar_back:
                finish();
                break;
        }
    }
}
