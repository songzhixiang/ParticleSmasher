package com.andy.library.rv;

import android.support.v4.widget.SwipeRefreshLayout;


/**
 * @author andysong
 * @data 2019-06-12
 * @discription xxx
 */
public class SwipeRefreshHelper {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private SwipRefreshListener mSwipRefreshListener;




    static SwipeRefreshHelper createSwipeRefreshHelper(SwipeRefreshLayout swipeRefreshLayout){
        return new SwipeRefreshHelper(swipeRefreshLayout);
    }


    private SwipeRefreshHelper(SwipeRefreshLayout swipeRefreshLayout) {
        mSwipeRefreshLayout = swipeRefreshLayout;
        init();
    }

    private void init() {

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (mSwipRefreshListener!=null){
                    mSwipRefreshListener.onRefresh();
                }
            }
        });
    }





    public void setSwipRefreshListener(SwipRefreshListener swipRefreshListener) {
        mSwipRefreshListener = swipRefreshListener;
    }


}
