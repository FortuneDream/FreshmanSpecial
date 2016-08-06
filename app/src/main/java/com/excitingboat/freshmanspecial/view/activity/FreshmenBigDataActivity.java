package com.excitingboat.freshmanspecial.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.excitingboat.freshmanspecial.R;
import com.excitingboat.yellowcake.Yellowcake;

public class FreshmenBigDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        Yellowcake yellowcake = (Yellowcake) findViewById(R.id.cake);
        yellowcake.setData(new int[]{3, 1, 3}, new int[]{0xFF66CCFF, 0xFFEE82EE, 0xFF66CCFF});


//        init();
    }

/*    private void init() {
        findViewById(R.id.bt_toolbar_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FreshmenBigDataActivity.this.finish();
            }
        });
        ((TextView)findViewById(R.id.tv_toolbar_title)).setText(R.string.freshman_big_data);
    }*/
}
