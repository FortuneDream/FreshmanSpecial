package com.excitingboat.freshmanspecial.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by PinkD on 2016/8/3.
 * FragmentStatePagerAdapter for ViewPager
 */
public class FreshmanPagerAdapter extends FragmentStatePagerAdapter{

    private String[] titles;
    private ArrayList<Fragment> fragments;

    public FreshmanPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public FreshmanPagerAdapter(FragmentManager fm, String[] titles, ArrayList<Fragment> fragments) {
        super(fm);
        this.titles = titles;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
