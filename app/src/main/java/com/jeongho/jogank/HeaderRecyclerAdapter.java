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

public class HeaderRecyclerAdapter<T> extends RecyclerView.Adapter<HeaderRecyclerAdapter.ViewHolder> {

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = null;
        switch (i) {
            case HEAD_TYPE:
                view = LayoutInflater.from(mContext).inflate(R.layout.header, viewGroup, false);
                break;
            case CONTENT_TYPE:
                view = LayoutInflater.from(mContext).inflate(R.layout.item, viewGroup, false);
                break;
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HeaderRecyclerAdapter.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        //headView + 1
        return mList.size() + 1;
    }

    private List<T> mList;
    private Context mContext;

    private static final int HEAD_TYPE = 1;
    private static final int CONTENT_TYPE = 2;


    public HeaderRecyclerAdapter(Context context, List<T> list) {
        mContext = context;
        mList = list;
    }

    //
    //    @Override
    //    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
    //        View view = null;
    //        switch (i){
    //            case HEAD_TYPE:
    //                view = LayoutInflater.from(mContext).inflate(R.layout.header, viewGroup, false);
    //                break;
    //            case CONTENT_TYPE:
    //                view = LayoutInflater.from(mContext).inflate(R.layout.item, viewGroup, false);
    //                break;
    //        }
    //        return new ViewHolder(view);
    //    }
    //
    //    @Override
    //    public void onBindViewHolder(ViewHolder viewHolder, int i) {
    //
    //    }
    //
    //
    //    @Override
    //    public int getItemCount() {
    //        //headView + 1
    //        return mList.size() + 1;
    //    }
    //
    class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(View itemView) {
            super(itemView);
        }
    }

    //
    @Override
    public int getItemViewType(int position) {


        return position == 0 ? HEAD_TYPE : CONTENT_TYPE;
    }
}
