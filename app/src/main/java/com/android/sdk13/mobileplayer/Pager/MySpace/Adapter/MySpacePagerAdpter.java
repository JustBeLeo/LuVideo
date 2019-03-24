package com.android.sdk13.mobileplayer.Pager.MySpace.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.android.sdk13.mobileplayer.Pager.MySpace.Fragment.MySpaceFragment;

import java.util.ArrayList;

public class MySpacePagerAdpter extends FragmentPagerAdapter {

    ArrayList<MySpaceFragment> list;
    public MySpacePagerAdpter(FragmentManager fm,ArrayList<MySpaceFragment> list) {
        super( fm );
        this.list = list;
    }

    @Override
    public Fragment getItem(int i) {
        return list.get( i );
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return list.get( position ).getTitle();
    }
}
