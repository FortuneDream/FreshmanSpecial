package com.excitingboat.freshmanspecial.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.excitingboat.freshmanspecial.R;

public class FreshmenCQUPTStyleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freshmen_cqupt_style);
        init();
    }

    private void init() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.freshmen_cqupt_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FreshmenCQUPTStyleActivity.this.finish();
            }
        });
    }
}
