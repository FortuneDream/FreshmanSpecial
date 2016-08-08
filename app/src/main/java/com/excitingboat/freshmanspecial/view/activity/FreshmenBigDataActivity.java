package com.excitingboat.freshmanspecial.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.excitingboat.freshmanspecial.R;
import com.excitingboat.freshmanspecial.view.adapter.FreshmanPagerAdapter;
import com.excitingboat.freshmanspecial.view.fragment.BigData.BigDataFragmentCake;

import java.util.ArrayList;

public class FreshmenBigDataActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<Fragment> fragments;
    private String[] titles = {"男女比例", "最难科目", "毕业去向"};

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
        ((TextView) findViewById(R.id.tv_toolbar_title)).setText(R.string.freshman_big_data);

        fragments = new ArrayList<>();
        fragments.add(new BigDataFragmentCake());
        fragments.add(new BigDataFragmentCake());
        fragments.add(new BigDataFragmentCake());
        tabLayout = (TabLayout) findViewById(R.id.freshmen_big_data_tabLayout);
        viewPager = (ViewPager) findViewById(R.id.freshmen_big_data_viewPager);
        tabLayout.setupWithViewPager(viewPager);
        FreshmanPagerAdapter adapter = new FreshmanPagerAdapter(getSupportFragmentManager());
        adapter.setTitles(titles);
        adapter.setFragments(fragments);
        viewPager.setAdapter(adapter);
    }
}
