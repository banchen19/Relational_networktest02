package com.example.relational_networktest02.ui.Ln_en;

import static android.content.ContentValues.TAG;

import static com.example.relational_networktest02.R.id.toolbar_ev;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.relational_networktest02.R;
import com.example.relational_networktest02.ui.home.Home_Dao.Home_dao_Dao;
import com.example.relational_networktest02.ui.home.Home_Dao.Home_dao_base;
import com.example.relational_networktest02.ui.home.Home_Dao.Home_dao_entity;
import com.example.relational_networktest02.ui.home.Home_Rv_Adapter;
import com.example.relational_networktest02.ui.home.Home_in_add.event_add.Event_add;
import com.example.relational_networktest02.ui.utility.BaseActivity_Utility;
import com.example.relational_networktest02.ui.utility.Img_Utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Lnformation_entry extends BaseActivity_Utility {

    TextView ie_name,ie_gender,ie_age,it_Birthday;
    Button btn_lnformation_entry;
    Home_dao_Dao home_dao_dao;
    Home_dao_base home_dao_base;
    List<Home_dao_entity> home_dao_entities;
    ImageView image_head;
    private static final int PERMISSION_REQUEST_READ_EXTERNAL_STORAGE = 1;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lnformation_entry);
        inti();
        ActionBar actionBar = getSupportActionBar();
        home_dao_base= Room.databaseBuilder(this,Home_dao_base.class,"Home_dao_base")
                .allowMainThreadQueries()
                .build();
        home_dao_dao=home_dao_base.getHome_dao_Dao();
        home_dao_entities=home_dao_dao.getAllHome_Item();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int date = bundle.getInt("id");
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Toolbar toolbar = findViewById(R.id.toolbar_ie_Sharing);
        //添加菜单
        toolbar.inflateMenu(R.menu.ien_menu);
        toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_ios_new_24); // 设置返回图标
        //监听菜单项
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                String msg = "";
                switch (menuItem.getItemId()) {
                    case R.id.action_share:
                        Intent shareIntent = new Intent(Intent.ACTION_SEND);
                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_TEXT, "让你看看我的大宝贝");
                        startActivity(shareIntent);
                        break;
                }
                if (!msg.equals("")) {
                    Toast.makeText(Lnformation_entry.this, msg, Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        ie_name.setText("名字："+home_dao_entities.get(date).getIADD_NAME());
        ie_gender.setText("性别："+home_dao_entities.get(date).getIADD_GENDER());
        it_Birthday.setText("生日："+home_dao_entities.get(date).getIADD_AGE());
//        int i=;
        //格式化对象
        try {
            String dateString=home_dao_entities.get(date).getIADD_AGE();
            @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            Date dates = format.parse(dateString);
            Calendar calendar = Calendar.getInstance();
            assert dates != null;
            calendar.setTime(dates);
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            ie_age.setText("年龄："+Date_Time_Utility(year,month,day));

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        requestReadExternalStoragePermission(image_head,date);
        Button btnInformationEntry = findViewById(R.id.btn_lnformation_entry);
        btnInformationEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Lnformation_entry.this, Lnformation_entry_en.class);
                startActivity(intent);
            }
        });

    }

    private void requestReadExternalStoragePermission(ImageView image_head,int id) {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // 如果应用之前请求过该权限但用户拒绝了请求，此方法将返回 true。
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                // 你可以在此处显示一个对话框解释为什么需要该权限，然后再次请求该权限。
            } else {
                // 申请该权限。
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PERMISSION_REQUEST_READ_EXTERNAL_STORAGE);
            }
        } else {
            // 已经拥有权限，可以执行相应操作了。
            // ...

            image_head.setImageBitmap(Img_Utility.ImageCrop(Img_Utility.openFile(home_dao_entities.get(id).getHEAD_ADDRESS()), true));
        }
    }
    private void inti() {
        ie_name=findViewById(R.id.ie_name);
        ie_gender=findViewById(R.id.ie_gender);
        ie_age=findViewById(R.id.ie_age);
        it_Birthday=findViewById(R.id.it_Birthday);
        image_head=findViewById(R.id.img_head_en);
        btn_lnformation_entry=findViewById(R.id.btn_lnformation_entry);

    }
    public int Date_Time_Utility(int year,int month,int day) throws ParseException {
// 获取当前日期
        Calendar today = Calendar.getInstance();
        int todayYear = today.get(Calendar.YEAR);
        int todayMonth = today.get(Calendar.MONTH);
        int todayDay = today.get(Calendar.DAY_OF_MONTH);

// 获取生日日期
        Calendar birthday = Calendar.getInstance();
        birthday.set(Calendar.YEAR, year);
        birthday.set(Calendar.MONTH, month); // 月份从0开始，所以这里表示9月
        birthday.set(Calendar.DAY_OF_MONTH, day);

// 计算年龄
        int age = todayYear - birthday.get(Calendar.YEAR);
        if (todayMonth < birthday.get(Calendar.MONTH)) {
            age--;
        } else if (todayMonth == birthday.get(Calendar.MONTH)
                && todayDay < birthday.get(Calendar.DAY_OF_MONTH)) {
            age--;
        }

// 输出年龄
//        System.out.println("年龄为：" + age);
        return age;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}