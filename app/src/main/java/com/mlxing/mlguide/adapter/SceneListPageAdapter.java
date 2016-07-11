package com.mlxing.mlguide.adapter;

import android.content.Context;

import com.mlxing.mlguide.R;
import com.mlxing.mlguide.adapter.base.ViewHolder;
import com.mlxing.mlguide.adapter.base.recyclerview.LoadMoreCommonAdapter;
import com.mlxing.mlguide.entity.Scene;

import java.util.ArrayList;

/**
 * Created by quan on 2016/6/20.
 */
public class SceneListPageAdapter extends LoadMoreCommonAdapter<Scene> {

    public SceneListPageAdapter(Context context) {
        super(context, R.layout.item_scene, new ArrayList<Scene>());
    }

    @Override
    public void convert(ViewHolder holder, Scene item) {
        holder.setImageByUrl(R.id.item_img, item.getListRightPic());
        holder.setText(R.id.item_name, item.getName());
        holder.setText(R.id.item_right, item.getCityName());

    }
}
