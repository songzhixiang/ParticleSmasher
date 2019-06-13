package com.andy.customview;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.andy.customview.muilt.MultiAdapter;
import com.andy.library.rv.ItemListener;
import com.andy.library.rv.RViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author andysong
 * @data 2019-06-13
 * @discription xxx
 */
public class MultiActivity extends BaseRViewActivity {

    private List<UserBean> mDatas = new ArrayList<>();
    private MultiAdapter mAdapter;
    private Context mContext;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        initDatas();
        linstener();
    }

    private void linstener() {
        mAdapter.setItemListener(new ItemListener<UserBean>() {
            @Override
            public void onItemClick(View view, UserBean entity, int postion) {
                Toast.makeText(mContext, "条目点击"+postion, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, UserBean entity, int postion) {
                Toast.makeText(mContext, "条目长安"+postion, Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void initDatas() {
        new Thread(() -> {
            if (mDatas.isEmpty()){
                for (int i = 0; i < 15; i++) {
                    for (int j = 1; j <=15; j++) {
                        UserBean userBean = new UserBean();
                        if (j%15 ==1){
                            userBean.setType(1);
                            userBean.setUserName("i am  no.1");
                        }else if (j%15 ==2 || j%15 ==3){
                            userBean.setType(2);
                            userBean.setUserName("i am  no.2");
                        }else {
                            userBean.setType(0);
                            userBean.setUserName("i am  no.0");
                        }
                        mDatas.add(userBean);
                    }
                }
            }

            notifyAdapterDataSetChange(mDatas);
        }).start();
    }

    @Override
    public RViewAdapter<UserBean> createViewAdapter() {
        mAdapter = new MultiAdapter(null);
        return mAdapter;
    }

    @Override
    public void onRefresh() {
        initDatas();
    }
}
