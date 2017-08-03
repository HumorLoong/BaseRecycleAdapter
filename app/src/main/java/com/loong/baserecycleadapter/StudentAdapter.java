package com.loong.baserecycleadapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lxl on 2017/7/17.
 */

public class StudentAdapter extends BaseRecycleAdapter<StudentAdapter.MViewHolder,Student> {
    public StudentAdapter(List<Student> mineDataList) {
        super(mineDataList);
    }

    @Override
    protected MViewHolder getViewHolder(ViewGroup parent) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_student,parent,false);
        setClickViewIds(R.id.name,R.id.age,R.id.study_class);
        return new MViewHolder(view);
    }

    @Override
    protected void onMyBindViewHolder(MViewHolder holder, int position, List<Student> mineDataList) {
        Student student=mineDataList.get(position);
        holder.name.setText(student.getName());
        holder.age.setText(student.getAge()+"");
        holder.className.setText(student.getStudyClass());
    }

    class MViewHolder extends RecyclerView.ViewHolder{
        TextView name,age,className;
        public MViewHolder(View itemView) {
            super(itemView);
            name= (TextView) itemView.findViewById(R.id.name);
            age= (TextView) itemView.findViewById(R.id.age);
            className= (TextView) itemView.findViewById(R.id.study_class);
        }
    }
}
