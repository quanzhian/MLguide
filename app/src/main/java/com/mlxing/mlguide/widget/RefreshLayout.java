package com.mlxing.mlguide.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mlxing.mlguide.R;

/**
 * SwipeRefreshLayout 组合 ListView进行上拉和下拉加载
 */
public class RefreshLayout extends SwipeRefreshLayout {

    private final int mTouchSlop;
    private ListView mListView;
    private OnLoadListener mOnLoadListener;

    private float firstTouchY;
    private float lastTouchY;

    private boolean isLoading = false;

    private View footerView;
    private TextView textMore;
    private ProgressBar progressBar;

    public RefreshLayout(Context context) {
        this(context, null);
    }

    public RefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    //set the child view of RefreshLayout,ListView

    public void setChildView(final ListView mListView) {
        this.mListView = mListView;
        footerView = View.inflate(getContext(), R.layout.loading_layout,null);
        this.mListView.addFooterView(footerView);
        textMore = (TextView) footerView.findViewById(R.id.tv_loading);
        progressBar = (ProgressBar) footerView.findViewById(R.id.progress_loading);
        textMore.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    case SCROLL_STATE_FLING:
                        break;
                    case SCROLL_STATE_IDLE:
                        if (canLoadMore()) {
                            loadData();
                        }
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                firstTouchY = event.getRawY();
                break;

            case MotionEvent.ACTION_UP:
                lastTouchY = event.getRawY();
                break;
            default:
                break;
        }

        return super.dispatchTouchEvent(event);
    }

    private boolean canLoadMore() {
        return isBottom() && !isLoading && isPullingUp();
    }

    private boolean isBottom() {
        if (mListView.getCount() > 0) {
            if (mListView.getLastVisiblePosition() == mListView.getAdapter().getCount() - 1 &&
                    mListView.getChildAt(mListView.getChildCount() - 1).getBottom() <= mListView.getHeight()) {

                return true;
            }
        }

        return false;
    }

    private boolean isPullingUp() {
        return (firstTouchY - lastTouchY) >= mTouchSlop;
    }

    private void loadData() {
        if (mOnLoadListener != null) {
            setLoading(true);
        }
    }

    public void setLoading(boolean loading) {
        if (mListView == null) return;
        isLoading = loading;
        if (loading) {
            if (isRefreshing()) {
                setRefreshing(false);
            }
            mListView.setSelection(mListView.getAdapter().getCount() - 1);
            textMore.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            mOnLoadListener.onLoad();
        } else {
            firstTouchY = 0;
            lastTouchY = 0;
            textMore.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }
    }

    public void onComplete(){
        setRefreshing(false);
        setLoading(false);
    }

    public void setOnLoadListener(OnLoadListener loadListener) {
        mOnLoadListener = loadListener;
    }

    public interface OnLoadListener {
        void onLoad();
    }
}
