package com.loong.baserecycleadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by lxl on 2017/6/14.
 * 简单的基础类，整合了整体点击和部分view的点击事件
 * 使用方法：
 * public class CodeAdapter extends BaseRecycleAdapter<CodeAdapter.MViewHolder,Integer> {
 * public CodeAdapter(List<Integer> mineDataList) {
 * super(mineDataList);
 * }
 *
 * @Override
 * protected MViewHolder getViewHolder(ViewGroup parent) {
 * View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_code,null);
 * setClickViewIds(R.id.code_num);//设置点击事件
 * return new MViewHolder(view);
 * }

 * @Override
 * protected void onMyBindViewHolder(MViewHolder holder, int position) {
 * holder.superTextView.setText(position+1+"");
 * }


 * public class MViewHolder extends RecyclerView.ViewHolder{
 * SuperTextView superTextView;
 * public MViewHolder(View itemView) {
 * super(itemView);
 * superTextView= (SuperTextView) itemView.findViewById(R.id.code_num);
 * }
 * }
 */

public abstract class BaseRecycleAdapter<VH extends RecyclerView.ViewHolder,V> extends RecyclerView.Adapter<VH> {
    private OnViewClickListener<V> onViewClickListener;
    private OnItemViewClickListener<V> onItemViewClickListener;
    private OnItemViewLongClickListener<V> onItemViewLongClickListener;
    private int[] intIds;
    private Context context;
    private List<V> mineDataList=new ArrayList<>();
    public BaseRecycleAdapter(List<V> mineDataList){
        this.mineDataList=mineDataList;
    }
    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context=parent.getContext();
        return getViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(VH holder, final int position) {
        if (onItemViewClickListener!=null)
            holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemViewClickListener.itemViewClick(mineDataList.get(position),position);
            }
        });
        if (onItemViewLongClickListener!=null){
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemViewLongClickListener.itemLongViewClick(mineDataList.get(position),position);
                    return false;
                }
            });
        }
        if (onViewClickListener!=null&&intIds!=null&&intIds.length>0){
            for (final int ids:intIds){
                View view=holder.itemView.findViewById(ids);
                if (view!=null){
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onViewClickListener.viewClick(mineDataList.get(position),ids,position);
                        }
                    });
                }else {
                    Toast.makeText(context,"The Id Isn't Belong The ViewHolder", Toast.LENGTH_SHORT).show();
                }
            }
        }
        onMyBindViewHolder(holder,position,mineDataList);
    }

    protected abstract VH getViewHolder(ViewGroup parent);
    protected abstract void onMyBindViewHolder(VH holder, final int position,List<V> mineDataList);

    @Override
    public int getItemCount() {
        return mineDataList.size();
    }


    public interface OnViewClickListener<V>{
        void viewClick(V v, int clickId, int position);
    }

    public interface OnItemViewClickListener<V>{
        void itemViewClick(V v, int position);
    }

    public interface OnItemViewLongClickListener<V>{
        void itemLongViewClick(V v, int position);
    }


    /**
     * 设置点击事件的ID
     * @param clickIds 要点击的id
     */
    protected void setClickViewIds(int... clickIds){
        this.intIds=clickIds;
    }


    /**
     *  设置VIew点击的回调监听
     * @param onViewClickListener view监听事件
     */
    public void setOnViewClickListener(OnViewClickListener<V> onViewClickListener){
        this.onViewClickListener=onViewClickListener;
    }

    /**
     *  整个view点击回调
     * @param onItemViewClickListener 回调函数
     */
    public void setOnItemViewClickListener(OnItemViewClickListener<V> onItemViewClickListener){
        this.onItemViewClickListener=onItemViewClickListener;
    }

    /**
     *  整个view点击回调
     * @param onItemViewLongClickListener 回调函数
     */
    public void setOnItemViewLongClickListener(OnItemViewLongClickListener<V> onItemViewLongClickListener){
        this.onItemViewLongClickListener=onItemViewLongClickListener;
    }


    /**
     *
     * @param value 增加的数据
     */
    public void addItemView(V value){
        addItemView(value,true);
    }

    /**
     *
     * @param value 增加的数据
     * @param isAgain 数据是否可以重复
     */
    public void addItemView(V value,boolean isAgain){
        if (isAgain) mineDataList.add(value);
        else {
            if (!mineDataList.contains(value))
                mineDataList.add(value);
        }
        notifyDataSetChanged();
    }

    /**
     *  删除数据
     * @param value 要删除的数据
     */
    public void removeItemView(V value){
        mineDataList.remove(value);
        notifyDataSetChanged();
    }

    /**
     * 删除数据
     * @param position 删除数据的地址
     */
    public void removeItemView(int position){
        mineDataList.remove(position);
        notifyDataSetChanged();
    }

    /**
     *  一次增加很多数据
     * @param values 要增加的数据
     */
    public void addSomeItemView(List<V> values){
        mineDataList.addAll(values);
        notifyDataSetChanged();
    }

    /**
     *  --清除数据
     */
    public void cleanView(){
        mineDataList=new ArrayList<>();
        notifyDataSetChanged();
    }

    /**
     *  刷新界面-
     */
    public void refreshView(){
        notifyDataSetChanged();
    }



    public void changeDataByPosition(int position,V v){
        mineDataList.remove(position);
        mineDataList.add(position,v);
        notifyDataSetChanged();
    }

    /**
     * 获取所有数据
     * @return 所有数据
     */
    public List<V> getAllData(){
        return mineDataList;
    }

}
