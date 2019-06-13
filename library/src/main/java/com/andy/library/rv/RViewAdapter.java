package com.andy.library.rv;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author andysong
 * @data 2019-06-12
 * @discription RecyclerView多item样式适配器
 */
public class RViewAdapter<T> extends RecyclerView.Adapter<RViewHolder> {

    private ItemListener<T> mItemListener;//条目点击监听

    private List<T> datas;//数据源

    private RViewItemManager<T>  itemStyle;//item类型的管理

    //如何判断当前是单样式还是多样式


    public RViewAdapter(List<T> datas) {
        if (datas==null){
            this.datas = new ArrayList<>();
        }
        this.datas = datas;
        itemStyle = new RViewItemManager();
    }

    //多样式
    public RViewAdapter(List<T> datas,RViewItem<T> item) {
        if (datas==null){
            this.datas = new ArrayList<>();
        }
        this.datas = datas;
        itemStyle = new RViewItemManager();

        //如果是多样式，就需要添加到item类型管理器中
        addItemStyles(item);
    }

    public void addItemStyles(RViewItem<T> item) {
        itemStyle.addStyles(item);
    }


    //添加数据
    public void  addDatas(List<T> datas){
        if (datas == null){
            return;
        }
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    //修改数据
    public void updateDatas(List<T> datas){
        if (datas == null){
            return;
        }
        this.datas = datas;
        notifyDataSetChanged();
    }

    //是否有多样式
    private boolean hasMuiltStyle(){
        return itemStyle.getItemViewStylesCount()>0;
    }


    //根据postion获取当前Item的布局类型
    @Override
    public int getItemViewType(int position) {
        if (hasMuiltStyle()){
            return itemStyle.getItemViewType(datas.get(position),position);
        }
        return super.getItemViewType(position);
    }


    //根据布局类型创建不同的Item的ViewHolder
    @NonNull
    @Override
    public RViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        RViewItem rViewItem = itemStyle.getRViewItem(viewType);
        int layoutId = rViewItem.getItemLayout();
        RViewHolder viewHolder = RViewHolder.createViewHolder(viewGroup.getContext(), viewGroup, layoutId);
        //设置item点击条目监听
        if (rViewItem.openClick()){
            setListener(viewHolder);
        }
        return viewHolder;
    }

    private void setListener(final RViewHolder holder) {
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (mItemListener!=null){
                    mItemListener.onItemClick(v,datas.get(position),position);
                }
            }
        });
        holder.getConvertView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int position = holder.getAdapterPosition();
                if (mItemListener!=null){
                    mItemListener.onItemLongClick(v,datas.get(position),position);
                    return true;//如果不反悔true，那么上面的onCLick也会触发
                }
                return false;
            }
        });
    }

    //数据绑定ViewHolder
    @Override
    public void onBindViewHolder(@NonNull RViewHolder holder, int position) {
        convert(holder,datas.get(position));
    }

    private void convert(RViewHolder holder, T entity) {
        itemStyle.convert(holder,entity,holder.getAdapterPosition());
    }

    @Override
    public int getItemCount() {
        return datas == null?0:datas.size();
    }

    public void setItemListener(ItemListener<T> itemListener) {
        mItemListener = itemListener;
    }
}
