package com.andy.library.rv;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author andysong
 * @data 2019-06-12
 * @discription Adapter中的ViewHolder
 */
public final class RViewHolder extends RecyclerView.ViewHolder {


    //一个条目有多个布局，多个空间


    private SparseArray<View> mViews;//View控件的集合

    private View mConvertView;//当前View对象



    private RViewHolder(@NonNull View itemView) {
        super(itemView);
        mConvertView = itemView;
        mViews = new SparseArray<>();
    }

    public static RViewHolder createViewHolder(Context context, ViewGroup parent,int layoutId){
        View itemView = LayoutInflater.from(context).inflate(layoutId,parent,false);
        return new RViewHolder(itemView);
    }

    //对外提供API


    public View getConvertView() {
        return mConvertView;
    }

    //获取某个具体的控件

    public <T extends View> T getView(int viewId){
        View view = mViews.get(viewId);
        if (view == null){
            view = mConvertView.findViewById(viewId);
            //key: R.id.xxx value TextView
            mViews.put(viewId,view);
        }
        return (T) view;
    }
}
