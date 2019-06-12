package com.andy.library.rv;

import android.view.View;

/**
 * @author andysong
 * @data 2019-06-12
 * @discription 条目的监听
 */
public interface ItemListener<T> {

    void onItemClick(View view,T entity,int postion);

    void onItemLongClick(View view,T entity,int postion);
}
