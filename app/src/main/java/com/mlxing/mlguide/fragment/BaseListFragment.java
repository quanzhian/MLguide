package com.mlxing.mlguide.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mlxing.mlguide.R;
import com.mlxing.mlguide.adapter.base.recyclerview.CommonAdapter;
import com.mlxing.mlguide.adapter.base.recyclerview.DividerItemDecoration;
import com.mlxing.mlguide.adapter.base.recyclerview.OnItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by quan on 2016/6/20.
 */
public abstract class BaseListFragment<T> extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,OnItemClickListener {

    @BindView(R.id.list)
    protected RecyclerView mRecyclerView;
    @BindView(R.id.swipe_container)
    protected SwipeRefreshLayout swipeContainer;

    protected LinearLayoutManager linearLayoutManager;

    protected CommonAdapter<T> adapter;

    public int mPageNo = 1,mPageSize = 10,mPageCount = 1;

    private boolean mIsFirstLoad = true;

    /**
     * 获取适配器
     * @return
     */
    protected abstract CommonAdapter<T> getAdapter();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.common_content_layout, null);
        ButterKnife.bind(this, view);
        adapter = getAdapter();
        adapter.setOnItemClickListener(this);
        // 线性布局管理器
        linearLayoutManager = new LinearLayoutManager(this.getActivity());
        // 设置布局管理器
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(adapter);
        //如果布局大小一致有利于优化
        mRecyclerView.setHasFixedSize(true);
        //设置Item增加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this.getActivity(), DividerItemDecoration.VERTICAL_LIST,5,getResources().getColor(android.R.color.white)));

        swipeContainer.setColorSchemeResources(R.color.google_blue,
                R.color.google_green,
                R.color.google_red,
                R.color.google_yellow);

        swipeContainer.setOnRefreshListener(this);
        init();
        return view;
    }

    protected void init(){

    }

    /**
     * 首次读取数据
     * @param isDelayed 是否延迟加载数据
     */
    public void firstloadData(boolean isDelayed) {
        if(mIsFirstLoad){
            if(isDelayed){
                swipeContainer.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeContainer.setRefreshing(true);
                        onRefresh();
                        mIsFirstLoad = false;
                    }
                },1000);
                return;
            }
            swipeContainer.setRefreshing(true);
            onRefresh();
            mIsFirstLoad = false;
        }
    }

    /**
     * 加载完成之后调用
     */
    protected void refreshComplete(){
        swipeContainer.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        mPageNo = 1;
        loadData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
