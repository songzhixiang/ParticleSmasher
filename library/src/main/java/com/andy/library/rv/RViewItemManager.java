package com.andy.library.rv;

import android.support.v4.util.SparseArrayCompat;
import android.util.SparseArray;

/**
 * @author andysong
 * @data 2019-06-12
 * @discription 多类型，多item的类型管理器
 */
public class RViewItemManager<T>  {

    //所有RViewItem集合,key:viewType  value :RVItem 条目对象

    private SparseArrayCompat<RViewItem<T> > styles = new SparseArrayCompat<>();

    //新加入一个item样式，放置末尾
    public void addStyles(RViewItem<T> item) {
        if (item!=null){
            styles.put(styles.size(),item);
        }
    }

    public int getItemViewStylesCount() {
        return styles.size();
    }

    //根据数据源位置返回某item类型的ViewType,Style集合中的key
    public int getItemViewType(T t, int position) {
        for (int i = 0; i < styles.size(); i++) {
            //索引为0就是第一类条目样式
            RViewItem<T> item = styles.valueAt(i);
            if (item.isItemView(t,position)){
                return styles.keyAt(i);
            }
        }
        throw  new IllegalArgumentException("该item没有匹配的RViewItem ");
    }

    //style集合中的value
    public RViewItem getRViewItem(int viewType) {
        return styles.get(viewType);
    }

    //视图和数据源进行绑定
    public void convert(RViewHolder holder, T entity, int adapterPosition) {
        for (int i = 0; i < styles.size(); i++) {
            RViewItem<T> trViewItem = styles.valueAt(i);
            if (trViewItem.isItemView(entity,adapterPosition)){
                //条目赋值过程
                trViewItem.convert(holder,entity,adapterPosition);
                return;
            }
        }
        throw  new IllegalArgumentException("该item没有匹配的RViewItem ");
    }
}
