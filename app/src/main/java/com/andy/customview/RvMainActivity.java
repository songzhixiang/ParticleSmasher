package com.andy.customview;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.andy.annotation.BindView;
import com.andy.annotation.onClick;
import com.andy.library.ButterKnife;
import com.andy.library.rv.ItemListener;
import com.andy.library.rv.RViewAdapter;
import com.andy.library.rv.RViewHolder;
import com.andy.library.rv.RViewItem;

import java.util.ArrayList;
import java.util.List;

public class RvMainActivity extends BaseRViewActivity {
    private List<UserBean> mUserBeans = new ArrayList<>();

    private RViewAdapter mRViewAdapter;
    private Context mContext;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;
        initDatas();
        listener();
    }

    private void listener() {
        mRViewAdapter.setItemListener(new ItemListener<UserBean>() {

            @Override
            public void onItemClick(View view, UserBean entity, int postion) {
                Toast.makeText(mContext, "onItemClick"+postion, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, UserBean entity, int postion) {
                Toast.makeText(mContext, "onItemLongClick"+postion, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public RViewAdapter createViewAdapter() {
        mRViewAdapter = new RViewAdapter<>(mUserBeans, new RViewItem() {
            @Override
            public int getItemLayout() {
                return R.layout.item_one;
            }

            @Override
            public boolean openClick() {
                return true;
            }

            @Override
            public boolean isItemView(Object entity, int postion) {
                return true ;
            }

            @Override
            public void convert(RViewHolder holder, Object entity, int postion) {
                TextView textView = holder.getView(R.id.mtv);
                textView.setText(entity.toString());
            }
        });
        return mRViewAdapter;
    }

    @Override
    public void onRefresh() {
        initDatas();
    }

    private void initDatas() {
        if (mUserBeans.isEmpty()){
            for (int i = 0; i < 100; i++) {
                mUserBeans.add(new UserBean("andy","nan",10));
            }
        }
        notifyAdapterDataSetChange(mUserBeans);
    }


}
