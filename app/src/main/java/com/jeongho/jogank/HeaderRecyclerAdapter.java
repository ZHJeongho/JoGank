package com.jeongho.jogank;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Jeongho on 2017/6/8.
 */

public abstract class HeaderRecyclerAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {
    private List<T> mList;
    private Context mContext;

    private static final int HEAD_TYPE = 1;
    private static final int CONTENT_TYPE = 2;

    abstract void convertViewHolder(BaseViewHolder baseViewHolder, T data, int itemType);
    abstract void convertHeaderHolder(BaseViewHolder baseViewHolder, T data, int itemType);

    public HeaderRecyclerAdapter(Context context, List<T> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = null;
        switch (i) {
            case HEAD_TYPE:
                view = LayoutInflater.from(mContext).inflate(R.layout.header, viewGroup, false);
                break;
            case CONTENT_TYPE:
                view = LayoutInflater.from(mContext).inflate(R.layout.item, viewGroup, false);
                break;
        }
        return new BaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder baseViewHolder, int i) {

        switch (getItemViewType(i)){
            case HEAD_TYPE:
                convertHeaderHolder(baseViewHolder, mList.get(i), getItemViewType(i));
                break;
            case CONTENT_TYPE:
                convertViewHolder(baseViewHolder, mList.get(i), getItemViewType(i));
                break;
        }


    }

    @Override
    public int getItemCount() {
        //headView + 1
        return mList.size();
    }


    //
    @Override
    public int getItemViewType(int position) {
        return position == 0 ? HEAD_TYPE : CONTENT_TYPE;
//        return CONTENT_TYPE;
    }
}
