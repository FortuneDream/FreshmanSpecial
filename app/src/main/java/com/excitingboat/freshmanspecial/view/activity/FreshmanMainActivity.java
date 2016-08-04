package com.excitingboat.freshmanspecial.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import com.excitingboat.freshmanspecial.R;

public class FreshmanMainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freshman_main);
        init();
    }

    private void init() {
        RelativeLayout toolbar = (RelativeLayout) findViewById(R.id.freshmen_main_toolbar);
        toolbar.findViewById(R.id.bt_toolbar_back).setOnClickListener(this);
        toolbar.findViewById(R.id.bt_toolbar_user).setOnClickListener(this);
        findViewById(R.id.freshman_guide_card).setOnClickListener(this);
        findViewById(R.id.freshman_big_data_card).setOnClickListener(this);
        findViewById(R.id.freshman_cqupt_card).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.freshman_guide_card:
                startActivity(new Intent(this, FreshmenGuideActivity.class));
                break;
            case R.id.freshman_big_data_card:
                //TODO:判断是否需要登录
                startActivity(new Intent(this, FreshmenBigDataActivity.class));
                break;
            case R.id.freshman_cqupt_card:
                startActivity(new Intent(this, FreshmenCQUPTStyleActivity.class));
                break;
            case R.id.bt_toolbar_back:
                finish();
                break;
            case R.id.bt_toolbar_user:
                //TODO:判断是否需要登录
                startActivity(new Intent(FreshmanMainActivity.this, FreshmanLoginActivity.class));
                break;
        }
    }
}
