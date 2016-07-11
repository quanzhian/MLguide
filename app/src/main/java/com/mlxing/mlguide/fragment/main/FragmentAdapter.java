package com.mlxing.mlguide.fragment.main;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.RelativeLayout;

import com.lhh.apst.library.AdvancedPagerSlidingTabStrip;
import com.lhh.apst.library.Margins;
import com.mlxing.mlguide.R;

/**
 * Created by quan on 2016/6/19.
 */
public class FragmentAdapter extends FragmentStatePagerAdapter implements
        AdvancedPagerSlidingTabStrip.IconTabProvider,
        AdvancedPagerSlidingTabStrip.LayoutProvider,
        AdvancedPagerSlidingTabStrip.TipsProvider{

    public static final int VIEW_FIRST 		= 0;
    public static final int VIEW_SECOND	    = 1;
    public static final int VIEW_THIRD       = 2;
    public static final int VIEW_FOURTH    = 3;
    private FirstFragment mFirstFragment = null;
    private SecondFragment mSecondFragment = null;
    private ThirdFragment mThirdFragment = null;
    private FourthFragment mFourthFragment = null;

    public static final int VIEW_SIZE = 4;

    private int mSize = 0;

    private Context context;

    public FragmentAdapter(Context context,FragmentManager fm , int mSize) {
        super(fm);
        this.mSize = mSize;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        if(position >= 0 && position < VIEW_SIZE){
            switch (position){
                case  VIEW_FIRST:
                    if(null == mFirstFragment)
                        mFirstFragment = FirstFragment.instance();
                    return mFirstFragment;

                case VIEW_SECOND:
                    if(null == mSecondFragment)
                        mSecondFragment = SecondFragment.instance();
                    return mSecondFragment;

                case VIEW_THIRD:
                    if(null == mThirdFragment)
                        mThirdFragment = ThirdFragment.instance();
                    return mThirdFragment;

                case VIEW_FOURTH:
                    if(null == mFourthFragment)
                        mFourthFragment = FourthFragment.instance();
                    return mFourthFragment;
                default:
                    break;
            }
        }
        return null;
    }

    @Override
    public int getCount() {
        return VIEW_SIZE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position >= 0 && position < VIEW_SIZE){
            switch (position){
                case  VIEW_FIRST:
                    return  "导游";
                case  VIEW_SECOND:
                    return  "动态";
                case  VIEW_THIRD:
                    return  "体验";
                case  VIEW_FOURTH:
                    return  "我的";
                default:
                    break;
            }
        }
        return null;
    }

    @Override
    public float getPageWeight(int position) {
        if(position >= 0 && position < VIEW_SIZE){
            switch (position){
                case  VIEW_FIRST:
                    return  0.92f;
                case  VIEW_SECOND:
                    return  1.0f;
                case  VIEW_THIRD:
                    return  1.0f;
                case  VIEW_FOURTH:
                    return  0.92f;
                default:
                    break;
            }
        }
        return 1.0f;
    }

    @Override
    public int[] getPageRule(int position) {
        if(position >= 0 && position < VIEW_SIZE){
            switch (position){
                case  VIEW_FIRST:
                    return  new int[]{
                            RelativeLayout.ALIGN_PARENT_LEFT};
                case  VIEW_SECOND:
                    return  new int[]{
                            RelativeLayout.ALIGN_PARENT_LEFT};
                case  VIEW_THIRD:
                    return  new int[]{
                            RelativeLayout.ALIGN_PARENT_RIGHT};
                case  VIEW_FOURTH:
                    return  new int[]{
                            RelativeLayout.ALIGN_PARENT_RIGHT};
                default:
                    break;
            }
        }
        return new int[0];
    }

    private Resources getResources(){
        return context.getResources();
    }

    @Override
    public Margins getPageMargins(int position) {
        if(position >= 0 && position < VIEW_SIZE){
            switch (position){
                case  VIEW_FIRST:
                    return  new Margins(getResources().getDimensionPixelSize(R.dimen.home_bar_icon_margins),0,0,0);
                case VIEW_SECOND:
                    return  null;
                case VIEW_THIRD:
                    return  null;
                case VIEW_FOURTH:
                    return  new Margins(0,0,getResources().getDimensionPixelSize(R.dimen.home_bar_icon_margins),0);
                default:
                    break;
            }
        }
        return null;
    }

    @Override
    public Integer getPageIcon(int index) {
        if(index >= 0 && index < VIEW_SIZE){
            switch (index){
                case  VIEW_FIRST:
                    return  R.drawable.ic_home_gray;
                case VIEW_SECOND:
                    return  R.drawable.ic_supervisor_account_gray;
                case VIEW_THIRD:
                    return  R.drawable.ic_search_gray;
                case VIEW_FOURTH:
                    return  R.drawable.ic_person_gray;
                default:
                    break;
            }
        }
        return 0;
    }

    @Override
    public Integer getPageSelectIcon(int index) {
        if(index >= 0 && index < VIEW_SIZE){
            switch (index){
                case  VIEW_FIRST:
                    return  R.drawable.ic_home_red;
                case VIEW_SECOND:
                    return  R.drawable.ic_supervisor_account_red;
                case VIEW_THIRD:
                    return  R.drawable.ic_search_red;
                case VIEW_FOURTH:
                    return  R.drawable.ic_person_red;
                default:
                    break;
            }
        }
        return 0;
    }

    @Override
    public Rect getPageIconBounds(int position) {
        return new Rect(0, 0, mSize, mSize);
    }

    @Override
    public int[] getTipsRule(int position) {
        if(position >= 0 && position < VIEW_SIZE){
            switch (position){
                case  VIEW_FIRST:
                    return  new int[]{
                            RelativeLayout.ALIGN_PARENT_LEFT};
                case  VIEW_SECOND:
                    return  new int[]{
                            RelativeLayout.ALIGN_PARENT_LEFT};
                case  VIEW_THIRD:
                    return  new int[]{
                            RelativeLayout.ALIGN_PARENT_RIGHT};
                case  VIEW_FOURTH:
                    return  new int[]{
                            RelativeLayout.ALIGN_PARENT_RIGHT};
                default:
                    break;
            }
        }
        return new int[0];
    }

    @Override
    public Margins getTipsMargins(int position) {
        if(position >= 0 && position < VIEW_SIZE){
            switch (position){
                case VIEW_FIRST:
                    return new Margins(4 *getResources().getDimensionPixelSize(R.dimen.psts_dot_m_right), 0, 0, 0);
                default:
                    break;
            }
        }
        return null;
    }

    @Override
    public Drawable getTipsDrawable(int position) {
        return null;
    }
}
