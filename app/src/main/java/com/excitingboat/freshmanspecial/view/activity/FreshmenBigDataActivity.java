package com.excitingboat.freshmanspecial.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.excitingboat.freshmanspecial.R;

public class FreshmenBigDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freshmen_big_data);
        init();
    }

    private void init() {
        findViewById(R.id.bt_toolbar_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FreshmenBigDataActivity.this.finish();
            }
        });
        ((TextView)findViewById(R.id.tv_toolbar_title)).setText(R.string.freshman_big_data);
    }
}
