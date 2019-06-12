package com.andy.library.rv;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

/**
 * @author andysong
 * @data 2019-06-12
 * @discription xxx
 */
public interface RViewCreate<T> {

    Context context();

    SwipeRefreshLayout createSw();

    RecyclerView createReycle();

    RViewAdapter<T> createViewAdapter();


    boolean isSupportPaging();
}
