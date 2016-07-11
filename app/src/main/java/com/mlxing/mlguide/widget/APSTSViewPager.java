package com.mlxing.mlguide.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by quan on 2016/6/19.
 */
public class APSTSViewPager extends ViewPager {
    private boolean mNoFocus = false; //if true, keep View don't move
    public APSTSViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public APSTSViewPager(Context context){
        this(context,null);
    }

    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (mNoFocus) {
            return false;
        }
        return super.onInterceptTouchEvent(event);
    }

    public void setNoFocus(boolean b){
        mNoFocus = b;
    }
}
