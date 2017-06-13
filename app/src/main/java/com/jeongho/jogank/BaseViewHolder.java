package com.jeongho.jogank;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by Jeongho on 2017/6/12.
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> mViews;
    private View mItemView;

    public BaseViewHolder(View itemView) {
        super(itemView);

        mItemView = itemView;
        mViews = new SparseArray<>();
    }

    public View getView(int resId){
        View view = mViews.get(resId);
        if (view == null){
            view = mItemView.findViewById(resId);
            mViews.put(resId, view);
        }
        return view;
    }
}
