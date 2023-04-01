package com.example.relational_networktest02.ui.home;

import static android.content.ContentValues.TAG;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.relational_networktest02.R;
import com.example.relational_networktest02.ui.Ln_en.Lnformation_entry;
import com.example.relational_networktest02.ui.home.Home_Dao.Home_dao_Dao;
import com.example.relational_networktest02.ui.home.Home_Dao.Home_dao_base;
import com.example.relational_networktest02.ui.home.Home_Dao.Home_dao_entity;
import com.example.relational_networktest02.ui.home.Home_in_add.event_add.Event_add;
import com.example.relational_networktest02.ui.utility.Img_Utility;

import java.util.List;

public class Home_Rv_Adapter extends RecyclerView.Adapter<Home_Rv_Adapter.InnerHolder> {
    Context context;
    List<Home_dao_entity> list;
    Home_dao_Dao home_dao_dao;
    Home_dao_base home_dao_base;
    Img_Utility img_utility;
    private static final int PERMISSION_REQUEST_READ_EXTERNAL_STORAGE = 1;
    private void requestReadExternalStoragePermission(@NonNull Home_Rv_Adapter.InnerHolder holder,Home_dao_entity home_dao_entity) {
        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // 如果应用之前请求过该权限但用户拒绝了请求，此方法将返回 true。
            if (!ActivityCompat.shouldShowRequestPermissionRationale((Activity) context,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        // 申请该权限。
                        ActivityCompat.requestPermissions((Activity) context,
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                PERMISSION_REQUEST_READ_EXTERNAL_STORAGE);
                    }  // 你可以在此处显示一个对话框解释为什么需要该权限，然后再次请求该权限。

        } else {
            // 已经拥有权限，可以执行相应操作了。
            // ...
            holder.img_head.setImageBitmap(Img_Utility.openFile(home_dao_entity.getHEAD_ADDRESS()));
        }
    }

    public Home_Rv_Adapter(Context context, List<Home_dao_entity> list) {
        this.context = context;
        this.list = list;
        home_dao_base = Room.databaseBuilder(context, Home_dao_base.class, "Home_dao_base")
                .allowMainThreadQueries()
                .build();
        home_dao_dao = home_dao_base.getHome_dao_Dao();
        img_utility=new Img_Utility(context);
    }

    @NonNull
    @Override
    public Home_Rv_Adapter.InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.iadd_re_item, parent, false);
        return new InnerHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull Home_Rv_Adapter.InnerHolder holder, @SuppressLint("RecyclerView") int position) {
        Home_dao_entity home_dao_entity = list.get(position);
        holder.iadd_name.setText("名字："+home_dao_entity.getIADD_NAME());
        holder.iadd_gender.setText("性别："+home_dao_entity.getIADD_GENDER());
        holder.iadd_age.setText("年龄："+home_dao_entity.getIADD_AGE());

        //操作图片需要权限
        requestReadExternalStoragePermission(holder,home_dao_entity);

        //点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent =new Intent();
//                intent.setClass(context, Lnformation_entry.class);
//                intent.putExtra ("id",position); //将 Bundle对象封装到Intent对象中
//                context.startActivity(intent);
                MyTask task = new MyTask(context, position);
                task.execute();
            }
        });
        // 声明PopupMenu

// RecyclerView中某个item的长按事件
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                deleteStudent(position);
                return true;
            }
        });
        //事件记录添加
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent();
                intent.setClass(context, Event_add.class);//设置跳转到的Activity
                intent.putExtra("id", position); //将用户名密码信息封装到 Bundle对象中
                context.startActivity(intent);
            }
        });
// 获取要显示的视图
    }
    // 删除对话框
    private void deleteStudent(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("删除提醒");
        builder.setMessage("您确定要删除" + list.get(position).getIADD_NAME() + "吗");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Home_dao_entity home_dao_entity = new Home_dao_entity(null, null, null, null, null, null, null, null, null);
                home_dao_entity.setId(list.get(position).getId());
                Log.d(TAG, "onClick: " + list.get(position).getId());
                home_dao_dao.deleteWords(home_dao_entity);
                list.remove(position);
                notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }
    private class MyTask extends AsyncTask<Void, Void, Void> {
        private final Context context;
        private final int id;

        public MyTask(Context context, int id) {
            this.context = context;
            this.id = id;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            // 执行耗时操作
            // 将需要传递的数据作为参数传递给异步任务
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            // 启动新的 Activity 并传递数据
            Intent intent = new Intent(context, Lnformation_entry.class);
            intent.putExtra("id", id);
            Bundle bundle = new Bundle();
            bundle.putInt("id", id);
            intent.putExtras(bundle);
            context.startActivity(intent);
            Log.d(TAG, "onPostExecute: 数据： "+id);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class InnerHolder extends RecyclerView.ViewHolder {
        TextView iadd_name, iadd_gender, iadd_age;
        ImageView img_head;
        Button button;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            iadd_name = itemView.findViewById(R.id.it_iadd_name);
            iadd_gender = itemView.findViewById(R.id.it_iadd_gender);
            iadd_age = itemView.findViewById(R.id.it_iadd_age);
            img_head = itemView.findViewById(R.id.img_head);
            button=itemView.findViewById(R.id.btn_event_add);
        }
    }
    // 修改数据并局部刷新
    public void updateData(int position) {
        notifyDataSetChanged();
    }
}
