package com.andy.library.rv;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * @author andysong
 * @data 2019-06-12
 * @discription 帮助管理类
 */
public class RViewHelper<T>  {


    private Context mContext;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private SwipeRefreshHelper mSwipeRefreshHelperl;
    private RecyclerView mRecyclerViewl;
    private RViewAdapter<T> mTRViewAdapter;
    private final int startPagerNumber = 1;
    private boolean isSupprtPaging;
    private SwipRefreshListener mSwipRefreshListener;
    private int currentPage;


    private RViewHelper(Builer<T>  tBuiler) {
        this.currentPage = this.startPagerNumber;
        this.mSwipeRefreshLayout = tBuiler.create.createSw();
        this.mContext = tBuiler.create.context();
        this.mRecyclerViewl = tBuiler.create.createReycle();
        this.mTRViewAdapter = tBuiler.create.createViewAdapter();
        this.isSupprtPaging = tBuiler.create.isSupportPaging();
        this.mSwipRefreshListener = tBuiler.mSwipRefreshListener;

        if (mSwipeRefreshLayout!=null){
            mSwipeRefreshHelperl = SwipeRefreshHelper.createSwipeRefreshHelper(mSwipeRefreshLayout);
        }

        init();
    }

    private void init() {
        //Recydel初始化
        mRecyclerViewl.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        mRecyclerViewl.setItemAnimator(new DefaultItemAnimator());
        if (mSwipeRefreshHelperl!=null){
            mSwipeRefreshHelperl.setSwipRefreshListener(new SwipRefreshListener() {
                @Override
                public void onRefresh() {
                    dismissRefresh();
                    //页码重置
                    currentPage = startPagerNumber;
                    if (mSwipRefreshListener !=null){
                        mSwipRefreshListener.onRefresh();
                    }
                }
            });
        }
    }

    private void dismissRefresh() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    public void notifyAdapterDataSetChange(List datas) {
        if (currentPage == startPagerNumber){
            mTRViewAdapter.updateDatas(datas);
        }else {
            mTRViewAdapter.addDatas(datas);
        }
        mRecyclerViewl.setAdapter(mTRViewAdapter);


    }

    public static class Builer<T>{
        private RViewCreate<T> create;//控件初始化
        private SwipRefreshListener mSwipRefreshListener;


        public Builer(RViewCreate<T> create, SwipRefreshListener swipRefreshListener) {
            this.create = create;
            mSwipRefreshListener = swipRefreshListener;
        }

        public RViewHelper build(){
            return new RViewHelper(this);
        }
    }
}
