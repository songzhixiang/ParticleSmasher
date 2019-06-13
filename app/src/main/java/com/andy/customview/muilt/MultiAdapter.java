package com.andy.customview.muilt;

import com.andy.customview.UserBean;
import com.andy.library.rv.RViewAdapter;

import java.util.List;

/**
 * @author andysong
 * @data 2019-06-13
 * @discription xxx
 */
public class MultiAdapter extends RViewAdapter<UserBean> {
    public MultiAdapter(List<UserBean> datas) {
        super(datas);
        addItemStyles(new AItems());
        addItemStyles(new BItems());
        addItemStyles(new CItems());
    }
}
