package com.mlxing.mlguide.adapter.base.recyclerview;

import android.content.Context;
import android.view.ViewGroup;

import com.mlxing.mlguide.R;
import com.mlxing.mlguide.adapter.base.ViewHolder;

import java.util.List;

/**
 * Created by quan on 2016/6/20.
 */
public abstract class LoadMoreCommonAdapter<T> extends CommonAdapter<T> {

    protected static final int TYPE_ITEM = 0;
    protected static final int TYPE_FOOTER = 1;

    protected static final int FOOTER_LAYOUT_ID = R.layout.loading_layout;

    public LoadMoreCommonAdapter(Context context,int layoutId, List<T> mDatas) {
        super(context,layoutId,mDatas);
    }

    /**
     * 传进来的 List 末尾增加一个 null
     * null 作为是上拉的 progressBar 的标记
     * http://android-pratap.blogspot.com/2015/06/endless-recyclerview-with-progress-bar.html
     * 参看这篇文章
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        T t = getItemByPos(position);
        if (t == null) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType){
        if (viewType == TYPE_ITEM) {
            ViewHolder viewHolder = ViewHolder.get(mContext, null, parent, mLayoutId, -1);
            setListener(parent, viewHolder, viewType);
            return viewHolder;
        } else if (viewType == TYPE_FOOTER) {
            ViewHolder viewHolder = ViewHolder.get(mContext, null, parent, FOOTER_LAYOUT_ID , -1);
            return viewHolder;
        }
        return null;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.updatePosition(position);
        //这时候 T is null，先把 footer 处理了
        if (holder.getLayoutId() ==  FOOTER_LAYOUT_ID) {
            return;
        }
        convert(holder, mDatas.get(position));
    }

    /**
     * 加载更多操作前调用,配合 #addData(List<T> mDatas)
     * 主要是显示loadMore 的footerView
     */
    public void prevLoadMoreData(){
        this.mDatas.add(null);
        notifyItemInserted(this.mDatas.size() - 1);
    }

    @Override
    public void addData(List<T> mData){
        if(mData == null || mData.isEmpty()){
            return;
        }
        //删除 footer
        if(this.mDatas.size()  > 0) {
            this.mDatas.remove(this.mDatas.size() - 1);
        }
        this.mDatas.addAll(mData);
        notifyDataSetChanged();
    }

}
