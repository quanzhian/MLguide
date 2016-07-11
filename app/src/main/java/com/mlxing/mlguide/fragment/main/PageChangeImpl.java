package com.mlxing.mlguide.fragment.main;

import android.support.v4.view.ViewPager;

import com.mlxing.mlguide.fragment.BaseListFragment;

/**
 * Created by quan on 2016/6/19.
 */
public class PageChangeImpl implements ViewPager.OnPageChangeListener {

    private FragmentAdapter adapter;

    public PageChangeImpl(FragmentAdapter adapter){
        this.adapter = adapter;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        ((BaseListFragment)adapter.getItem(position)).firstloadData(false);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
