package com.example.relational_networktest02.ui.home.Home_in_add.ui;

import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.relational_networktest02.MainActivity;
import com.example.relational_networktest02.R;
import com.example.relational_networktest02.ui.Ln_en.Lnformation_entry;
import com.example.relational_networktest02.ui.home.Home_Dao.Home_dao_Dao;
import com.example.relational_networktest02.ui.home.Home_Dao.Home_dao_base;
import com.example.relational_networktest02.ui.home.Home_Dao.Home_dao_entity;
import com.example.relational_networktest02.ui.utility.BaseActivity_Utility;
import com.example.relational_networktest02.ui.utility.DatePickerFragment;
import com.example.relational_networktest02.ui.utility.Img_Utility;

import java.util.List;

public class Lnformation_add extends BaseActivity_Utility {
    private static final int REQUEST_PICK_IMAGE = 1; // 请求码，用于在onActivityResult中获取选中图片的路径
    Button btn;
    EditText iadd_name, iadd_gender, iadd_nationality, iadd_height, iadd_weight, iadd_identity, iadd_number;

    String a, b, c, d, e, f, g, h;
    TextView iadd_age;

    //room sqlite数据库
    Home_dao_base home_dao_base;
    List<Home_dao_entity> home_dao_entities;
    Home_dao_Dao home_dao_dao;
    ImageView Head_address;
    String Head_address_path=null;
    Img_Utility img_utility;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lnformation_add);
        home_dao_base = Room.databaseBuilder(this, Home_dao_base.class, "Home_dao_base")
                .allowMainThreadQueries()
                .build();
        home_dao_dao = home_dao_base.getHome_dao_Dao();
        inti();
        home_dao_entities = home_dao_dao.getAllHome_Item();
        btn = findViewById(R.id.btn_en_add);
        img_utility=new Img_Utility(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                extracted();
                Intent intent = new Intent();
                intent.setClass(Lnformation_add.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(Lnformation_add.this, "保存信息", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        //头像处理
        Head_address = findViewById(R.id.Head_address);
        Head_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Img_Utility.pickImageFromGallery(REQUEST_PICK_IMAGE);
            }
        });
        iadd_age.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                // 在Activity中打开时间选择器
                // 创建DatePickerFragment实例
                DatePickerFragment datePickerFragment = new DatePickerFragment();

// 设置DatePickerFragment回调接口
                datePickerFragment.setListener((view1, year, month, dayOfMonth) -> {
                    // 在这里处理选中的日期
                    iadd_age.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                });

// 显示DatePickerFragment
                datePickerFragment.show(getSupportFragmentManager(), "datePicker");

            }
        });

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Toolbar toolbar = findViewById(R.id.toolbar_i_a);
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
                    Toast.makeText(Lnformation_add.this, msg, Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent();
                intent.setClass(Lnformation_add.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    // 在onActivityResult中获取选中图片的路径
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_PICK_IMAGE && resultCode == RESULT_OK) {
            Uri selectedImageUri = data.getData();
            String imagePath = img_utility.getRealPathFromURI(selectedImageUri);
            Head_address_path=imagePath;
            Head_address.setImageBitmap(img_utility.openFile(imagePath));
            // 在这里可以将选中的图片路径传递给其他方法进行后续处理
        }
    }



    @SuppressLint("WrongViewCast")
    private void inti() {
        iadd_name = findViewById(R.id.iadd_name);
        iadd_gender = findViewById(R.id.iadd_gender);
        iadd_age = findViewById(R.id.iadd_age);
        iadd_nationality = findViewById(R.id.iadd_nationality);
        iadd_height = findViewById(R.id.iadd_height);
        iadd_weight = findViewById(R.id.iadd_weight);
        iadd_identity = findViewById(R.id.iadd_identity);
        iadd_number = findViewById(R.id.iadd_number);
    }

    private void extracted() {
//        Log.d(TAG, "extracted: "+iadd_name.getText());
        a = String.valueOf(iadd_name.getText());
        b = String.valueOf(iadd_gender.getText());
        c = String.valueOf(iadd_age.getText());
        d = String.valueOf(iadd_nationality.getText());
        e = String.valueOf(iadd_height.getText());
        f = String.valueOf(iadd_weight.getText());
        g = String.valueOf(iadd_identity.getText());
        h = String.valueOf(iadd_number.getText());
        if(Head_address_path!=null) {
            Home_dao_entity home_dao_entity = new Home_dao_entity(Head_address_path,
                    a, b, c, d, e, f, g, h);
            home_dao_dao.insertWords(home_dao_entity);
        }else
        {
            Home_dao_entity home_dao_entity = new Home_dao_entity("path",
                    a, b, c, d, e, f, g, h);
            home_dao_dao.insertWords(home_dao_entity);
        }
    }

    /**
     * 返回键
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        Intent intent = new Intent();
        intent.setClass(Lnformation_add.this, MainActivity.class);
        startActivity(intent);

    }

}