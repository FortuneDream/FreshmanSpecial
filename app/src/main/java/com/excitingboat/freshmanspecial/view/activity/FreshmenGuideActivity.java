package com.excitingboat.freshmanspecial.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.excitingboat.freshmanspecial.R;
import com.excitingboat.freshmanspecial.model.bean.Dormitory;
import com.excitingboat.freshmanspecial.net.GetInformation;
import com.excitingboat.freshmanspecial.presenter.GetInformationPresenter;
import com.excitingboat.freshmanspecial.utils.BitmapUtil;
import com.excitingboat.freshmanspecial.utils.ScreenUtils;
import com.excitingboat.freshmanspecial.view.adapter.FreshmanPagerAdapter;
import com.excitingboat.freshmanspecial.view.fragment.FreshmanGuide.DormitorySituationFragment;
import com.excitingboat.freshmanspecial.view.fragment.FreshmanGuide.EnrolInformationFragment;
import com.excitingboat.freshmanspecial.view.fragment.FreshmanGuide.EnrolWayFragment;
import com.excitingboat.freshmanspecial.view.fragment.FreshmanGuide.InfoPageFragment;
import com.excitingboat.freshmanspecial.view.fragment.FreshmanGuide.NecessaryList;
import com.excitingboat.freshmanspecial.view.fragment.FreshmanGuide.QQGroup;

import java.util.ArrayList;

public class FreshmenGuideActivity extends AppCompatActivity {
    ImageButton back;
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
        back = (ImageButton) findViewById(R.id.bt_toolbar_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // showBigPicture(view);
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
            if (i == 5 || i == 6 || i == 7) {
                mFragments.add(InfoPageFragment.newInstance(i));
            } else {
                if (titles[i].equals("入学须知")) {
                    mFragments.add(new EnrolInformationFragment());
                } else if (titles[i].equals("须知路线")) {
                    mFragments.add(new EnrolWayFragment());
                } else if (titles[i].equals("必备清单")) {
                    mFragments.add(new NecessaryList());
                } else if (titles[i].equals("QQ群")) {
                    mFragments.add(new QQGroup());
                } else if(titles[i].equals("寝室概况")){
                    DormitorySituationFragment dormitorySituationFragment = new DormitorySituationFragment();
                    dormitorySituationFragment.setPresenter(new GetInformationPresenter<>(dormitorySituationFragment, GetInformation.DORMITORY));
                    mFragments.add(dormitorySituationFragment);
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

    public void showBigPicture(View view){

        ShowBigPictureActivity.showBigPicture(FreshmenGuideActivity.this,
                "http://img5.imgtn.bdimg.com/it/u=471926043,2104091042&fm=21&gp=0.jpg",
                view
                );
        //添加过渡动画，一定要添加！！
        overridePendingTransition(0, R.anim.abc_fade_in);
    }

}
