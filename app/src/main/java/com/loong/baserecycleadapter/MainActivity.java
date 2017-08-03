package com.loong.baserecycleadapter;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private StudentAdapter adapter;
    private List<Student> studentList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewAndData();
    }

    private void initViewAndData() {
        RecyclerView recyclerView= (RecyclerView) findViewById(R.id.student_recycle);

        studentList=new ArrayList<>();
        studentList.add(getStudent());
        adapter=new StudentAdapter(studentList);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        initClick();
    }

    private void initClick() {
        findViewById(R.id.add).setOnClickListener(this);
        findViewById(R.id.del).setOnClickListener(this);
        findViewById(R.id.del_all).setOnClickListener(this);

        /**
         * 设置Item的点击事件和回调
         */
       adapter.setOnItemViewClickListener(new BaseRecycleAdapter.OnItemViewClickListener<Student>() {
           @Override
           public void itemViewClick(Student student, int position) {
               Toast.makeText(MainActivity.this, "相应点击事件", Toast.LENGTH_SHORT).show();
               showDialog(student.toString());
           }
       });

        /**
         * 设置Item的长按事件和回调
         */
        adapter.setOnItemViewLongClickListener(new BaseRecycleAdapter.OnItemViewLongClickListener<Student>() {
            @Override
            public void itemLongViewClick(Student student, int position) {
               adapter.removeItemView(position);
                Toast.makeText(MainActivity.this, "成功删除学生:"+student.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        /**
         * 设置item内部View的点击事件和回调
         */
        adapter.setOnViewClickListener(new BaseRecycleAdapter.OnViewClickListener<Student>() {
            @Override
            public void viewClick(Student student, int clickId, int position) {
                switch (clickId){
                    case R.id.name:
                        Toast.makeText(MainActivity.this, "点击学生姓名："+student.getName(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.age:
                        Toast.makeText(MainActivity.this, "点击了学生年龄"+student.getAge(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.study_class:
                        Toast.makeText(MainActivity.this, "点击了学生班级"+student.getStudyClass(), Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add:
                /**
                 * 增加数据
                 */
                adapter.addItemView(getStudent());
                break;
            case R.id.del:
                /**
                 * 删除数据
                 */
                adapter.removeItemView(adapter.getItemCount()-1);
                break;
            case R.id.del_all:
                /**
                 * 清空数据
                 */
                adapter.cleanView();
                break;
            case R.id.all_data:
                /**
                 * 获取所有数据
                 */
                List<Student> students=adapter.getAllData();
                break;

        }
    }

    private Student getStudent(){
        Student student=new Student();
        Random random=new Random();
        int randomNum=random.nextInt(20);
        student.setAge(randomNum);
        student.setName("大佬"+randomNum);
        student.setStudyClass("五年级"+randomNum+"班");
        return student;
    }

    private void showDialog(String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }
}
