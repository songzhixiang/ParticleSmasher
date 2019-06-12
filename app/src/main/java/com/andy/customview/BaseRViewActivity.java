package com.andy.customview;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.andy.library.rv.RViewHelper;
import com.andy.library.rv.SwipRefreshListener;
import com.andy.library.rv.SwipeRefreshHelper;
import com.andy.library.rv.RViewCreate;

import java.util.List;

/**
 * @author andysong
 * @data 2019-06-12
 * @discription xxx
 */
public abstract class BaseRViewActivity extends AppCompatActivity

        implements RViewCreate, SwipRefreshListener {

    protected RViewHelper mRViewHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        mRViewHelper = new RViewHelper.Builer<>(this,this).build();
    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    public SwipeRefreshLayout createSw() {
        return findViewById(R.id.swiprefresh);
    }

    @Override
    public RecyclerView createReycle() {
        return findViewById(R.id.recyclerview);
    }


    @Override
    public boolean isSupportPaging() {
        return false;
    }

    protected void notifyAdapterDataSetChange(List datas){
        mRViewHelper.notifyAdapterDataSetChange(datas);
    }
}
