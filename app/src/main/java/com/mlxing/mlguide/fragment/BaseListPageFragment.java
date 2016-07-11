package com.mlxing.mlguide.fragment;

import android.support.v7.widget.RecyclerView;

/**
 * Created by quan on 2016/6/20.
 */
public abstract class BaseListPageFragment<T> extends BaseListFragment<T> {

    boolean isLoading = false;

    @Override
    protected void init() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();

                if (lastVisibleItemPosition + 1 == adapter.getItemCount()) {
                    boolean isRefreshing = swipeContainer.isRefreshing();
                    if (isRefreshing) {
                        return;
                    }
                    if (!isLoading) {
                        isLoading = true;
                        if(mPageNo < mPageCount){
                            mPageNo++;
                            adapter.prevLoadMoreData();
                            loadData();
                        }
                    }
                }
            }
        });
    }

    @Override
    protected void refreshComplete() {
        isLoading = false;
        super.refreshComplete();
    }
}
