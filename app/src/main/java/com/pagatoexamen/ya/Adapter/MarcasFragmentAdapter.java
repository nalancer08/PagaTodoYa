package com.pagatoexamen.ya.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Revision 1 - 19/02/18
 */

public class MarcasFragmentAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> mFragments;
    ArrayList<String> mTitles;
    FragmentManager mFragmentManager;

    public MarcasFragmentAdapter(FragmentManager fm) {

        super(fm);
        this.mFragmentManager = fm;
        this.mFragments = new ArrayList<>();
        this.mTitles = new ArrayList<>();
    }

    public MarcasFragmentAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {

        super(fm);
        this.mFragmentManager = fm;
        this.mFragments = fragments;
        this.mTitles = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {

        return this.mFragments.get(position);
    }

    @Override
    public int getCount() {

        return mFragments.size();
    }

    public void addFragment(Fragment fragment, String title) {

        mFragments.add(fragment);
        mTitles.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}