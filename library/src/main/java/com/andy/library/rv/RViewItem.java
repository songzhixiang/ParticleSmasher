package com.andy.library.rv;

/**
 * @author andysong
 * @data 2019-06-12
 * @discription 当成Javabean对象
 */
public interface RViewItem<T> {

    //获取item布局
    int getItemLayout();

    //是否开启点击
    boolean openClick();

    //那个条目用那个布局
    //是否为当前item布局
    boolean isItemView(T entity,int postion);

    //将item的控件与需要显示的数据绑定
    void convert(RViewHolder holder,T entity,int postion);
}
