package com.excitingboat.freshmanspecial.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.excitingboat.freshmanspecial.R;
import com.excitingboat.freshmanspecial.view.adapter.FreshmanPagerAdapter;

import java.util.ArrayList;

public class FreshmenGuideActivity extends AppCompatActivity {
    private static final String[] titles = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freshmen_guide);
        init();
    }

    private void init() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.freshmen_guide_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FreshmenGuideActivity.this.finish();
            }
        });
        initTab();
    }

    private void initTab() {
        TabLayout tabLayout = (TabLayout) findViewById(R.id.freshmen_guide_tabLayout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.freshmen_guide_viewPager);
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new Fragment());
        viewPager.setAdapter(new FreshmanPagerAdapter(getSupportFragmentManager(), titles, fragments));
        tabLayout.setupWithViewPager(viewPager);
    }
}
