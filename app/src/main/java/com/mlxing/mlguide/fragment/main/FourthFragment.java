package com.mlxing.mlguide.fragment.main;

import android.view.View;
import android.view.ViewGroup;

import com.mlxing.mlguide.adapter.SceneListPageAdapter;
import com.mlxing.mlguide.adapter.base.recyclerview.CommonAdapter;
import com.mlxing.mlguide.api.ScenicService;
import com.mlxing.mlguide.api.base.BeanCallBack;
import com.mlxing.mlguide.api.base.ClientJsonResp;
import com.mlxing.mlguide.entity.Scene;
import com.mlxing.mlguide.fragment.BaseListPageFragment;
import com.mlxing.mlguide.utils.UIHelp;

import java.util.List;

import okhttp3.Call;

/**
 * Created by linhonghong on 2015/8/11.
 */
public class FourthFragment extends BaseListPageFragment<Scene> {

    public static FourthFragment instance() {
        FourthFragment view = new FourthFragment();
        return view;
    }

    @Override
    protected CommonAdapter<Scene> getAdapter() {
        return new SceneListPageAdapter(getActivity());
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        String ad_url = "http://www.zttmall.com/Wapshop/Topic.aspx?TopicId=18";
        String title = "百度一下你就知道";
        UIHelp.showWebViewActivity(getActivity(), title, ad_url);
    }

    @Override
    public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
        return false;
    }


    @Override
    public void loadData() {
        ScenicService.loadData("北京", null, null, mPageNo, mPageSize).execute(new BeanCallBack<ClientJsonResp<List<Scene>>>() {

            @Override
            public void onError(Call call, Exception e, int id) {
                UIHelp.toastMessage("加载数据出现错误,请重试!");
                refreshComplete();
            }

            @Override
            public void onResponse(ClientJsonResp<List<Scene>> response, int id) {
                mPageCount = response.getPageCount();
                adapter.addData(response.getResponse());
                refreshComplete();
            }
        });
    }

    @Override
    public void onRefresh() {
        mPageNo = 1;
        loadData();
    }
}