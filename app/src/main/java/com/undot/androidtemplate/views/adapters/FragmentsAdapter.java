package com.undot.androidtemplate.views.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by 0503337710 on 02/04/2016.
 */
public class FragmentsAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentsList;
    public FragmentsAdapter(FragmentManager fragmentManager, List<Fragment> fragmentList) {
        super(fragmentManager);
        this.fragmentsList = fragmentList;
    }


    @Override
    public Fragment getItem(int position) {
        return fragmentsList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentsList.size();
    }
}
