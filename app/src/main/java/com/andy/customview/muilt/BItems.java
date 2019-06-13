package com.andy.customview.muilt;

import android.widget.TextView;

import com.andy.customview.R;
import com.andy.customview.UserBean;
import com.andy.library.rv.RViewHolder;
import com.andy.library.rv.RViewItem;

/**
 * @author andysong
 * @data 2019-06-13
 * @discription xxx
 */
public class BItems implements RViewItem<UserBean> {
    @Override
    public int getItemLayout() {
        return R.layout.item_two;
    }

    @Override
    public boolean openClick() {
        return true;
    }

    @Override
    public boolean isItemView(UserBean entity, int postion) {
        return entity.getType() == 1;
    }

    @Override
    public void convert(RViewHolder holder, UserBean entity, int postion) {
        TextView mTextView = holder.getView(R.id.mtv);
        mTextView.setText(entity.getType()+">>>>>>>");
    }
}
