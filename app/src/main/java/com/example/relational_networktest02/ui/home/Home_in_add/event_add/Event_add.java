package com.example.relational_networktest02.ui.home.Home_in_add.event_add;

import static com.example.relational_networktest02.R.id.toolbar_ev;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.relational_networktest02.R;
import com.example.relational_networktest02.ui.Ln_en.Lnformation_entry;
import com.example.relational_networktest02.ui.utility.BaseActivity_Utility;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Event_add extends BaseActivity_Utility {

    FloatingActionButton button_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_add);

        button_add=findViewById(R.id.fb_btn_event_add_a);
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Toolbar toolbar = findViewById(toolbar_ev);
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
                    Toast.makeText(Event_add.this, msg, Toast.LENGTH_SHORT).show();
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
    }
}