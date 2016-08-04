package com.excitingboat.freshmanspecial.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.excitingboat.freshmanspecial.R;
import com.excitingboat.freshmanspecial.view.adapter.FreshmanPagerAdapter;
import com.excitingboat.freshmanspecial.view.fragment.FreshmanGuide.EnrolInformationFragment;
import com.excitingboat.freshmanspecial.view.fragment.FreshmanGuide.EnrolWayFragment;
import com.excitingboat.freshmanspecial.view.fragment.FreshmanGuide.InfoPageFragment;
import com.excitingboat.freshmanspecial.view.fragment.FreshmanGuide.NecessaryList;
import com.excitingboat.freshmanspecial.view.fragment.FreshmanGuide.QQGroup;

import java.util.ArrayList;

public class FreshmenGuideActivity extends AppCompatActivity {

    TextView title;
    TabLayout tabLayout;
    ViewPager viewPager;
    FreshmanPagerAdapter freshmanPagerAdapter;
    //设置ViewPager的page_id
    int ViewPagerId[] = new int[]{0, 1, 2, 3, 4, 5, 6, 7};
    //储存fragment的数组
    private ArrayList<Fragment> mFragments;
    //tab条目中的标题
    private String[] titles = {"入学须知", "须知路线", "寝室概况", "必备清单", "QQ群", "日常生活", "周边美食", "周边美景"};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freshmen_guide);
        initView();
    }

    private void initView() {
        initToolbar();

        initTab();
    }


    private void initToolbar() {
        findViewById(R.id.bt_toolbar_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        title = (TextView) findViewById(R.id.tv_toolbar_title);
        title.setText("新生攻略");

    }

    private void initTab() {
        tabLayout = (TabLayout) findViewById(R.id.freshmen_guide_tabLayout);
        viewPager = (ViewPager) findViewById(R.id.freshmen_guide_viewPager);
        freshmanPagerAdapter = new FreshmanPagerAdapter(getSupportFragmentManager());
        freshmanPagerAdapter.setTitles(titles);
        mFragments = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            if (i == 2 || i == 5 || i == 6 || i == 7) {
                mFragments.add(InfoPageFragment.newInstance(ViewPagerId[i]));
            } else {
                if (titles[i].equals("入学须知")) {
                    mFragments.add(new EnrolInformationFragment());
                } else if (titles[i].equals("须知路线")) {
                    mFragments.add(new EnrolWayFragment());
                } else if (titles[i].equals("必备清单")) {
                    mFragments.add(new NecessaryList());
                } else if (titles[i].equals("QQ群")) {
                    mFragments.add(new QQGroup());
                }
            }

        }

        freshmanPagerAdapter.setFragments(mFragments);

        //设置tablayout的模式
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        //给viewPager设置适配器
        viewPager.setAdapter(freshmanPagerAdapter);
        //TabLayout绑定ViewPager
        tabLayout.setupWithViewPager(viewPager);
    }
}
