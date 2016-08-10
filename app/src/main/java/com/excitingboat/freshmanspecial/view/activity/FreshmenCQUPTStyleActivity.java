package com.excitingboat.freshmanspecial.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.excitingboat.freshmanspecial.R;
import com.excitingboat.freshmanspecial.net.GetInformation;
import com.excitingboat.freshmanspecial.presenter.GetInformationPresenter;
import com.excitingboat.freshmanspecial.view.adapter.FreshmanPagerAdapter;
import com.excitingboat.freshmanspecial.view.fragment.Style.BeautifulCQUPTFragment;
import com.excitingboat.freshmanspecial.view.fragment.Style.OrganizationFragment;
import com.excitingboat.freshmanspecial.view.fragment.Style.PictureGridFragment;
import com.excitingboat.freshmanspecial.view.fragment.Style.PictureListFragment;

import java.util.ArrayList;

public class FreshmenCQUPTStyleActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<Fragment> fragments;
    private String[] titles = {"学生组织", "原创重邮", "美在重邮", "优秀学子", "优秀教师"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freshmen_cqupt_style);
        init();
    }

    private void init() {
        findViewById(R.id.bt_toolbar_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FreshmenCQUPTStyleActivity.this.finish();
            }
        });
        ((TextView) findViewById(R.id.tv_toolbar_title)).setText(R.string.freshman_cqupt_style);


        fragments = new ArrayList<>();
        OrganizationFragment fragment1 = new OrganizationFragment();
        PictureListFragment fragment2 = new PictureListFragment();
        fragment2.setPresenter(new GetInformationPresenter<>(fragment2, GetInformation.VIDEO));

        BeautifulCQUPTFragment fragment3 = new BeautifulCQUPTFragment();
        PictureGridFragment fragment4 = new PictureGridFragment();
        fragment4.setPresenter(new GetInformationPresenter<>(fragment4, GetInformation.STUDENT));
        PictureGridFragment fragment5 = new PictureGridFragment();
        fragment5.setPresenter(new GetInformationPresenter<>(fragment5, GetInformation.TEACHER));
        //TODO Add Adapter
//        fragment1.setBigDataAdapter(new BigDataAdapter_1());
//        fragment2.setBigDataAdapter(new BigDataAdapter_2());
//        fragment3.setBigDataAdapter(new BigDataAdapter_3());
        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);
        fragments.add(fragment4);
        fragments.add(fragment5);
        tabLayout = (TabLayout) findViewById(R.id.freshmen_cqupt_tabLayout);
        viewPager = (ViewPager) findViewById(R.id.freshmen_cqupt_viewPager);
        tabLayout.setupWithViewPager(viewPager);
        FreshmanPagerAdapter adapter = new FreshmanPagerAdapter(getSupportFragmentManager());
        adapter.setTitles(titles);
        adapter.setFragments(fragments);
        viewPager.setAdapter(adapter);

    }
}
