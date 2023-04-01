package com.example.relational_networktest02.ui.home;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.relational_networktest02.MainActivity;
import com.example.relational_networktest02.R;
import com.example.relational_networktest02.databinding.FragmentHomeBinding;
import com.example.relational_networktest02.ui.Ln_en.Lnformation_entry;
import com.example.relational_networktest02.ui.home.Home_Dao.Home_dao_Dao;
import com.example.relational_networktest02.ui.home.Home_Dao.Home_dao_base;
import com.example.relational_networktest02.ui.home.Home_Dao.Home_dao_entity;
import com.example.relational_networktest02.ui.home.Home_in_add.ui.Lnformation_add;

import java.util.List;

public class HomeFragment extends Fragment {

    //dao与数据库
    Home_dao_base home_dao_base;
    Home_dao_Dao home_dao_dao;
    Home_Rv_Adapter home_adapter;
    List<Home_dao_entity> home_dao_entities;
    private FragmentHomeBinding binding;

    Button btn_clear;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        home_dao_base = Room.databaseBuilder(getContext(), Home_dao_base.class, "Home_dao_base")
                .allowMainThreadQueries()
                .build();
        home_dao_dao = home_dao_base.getHome_dao_Dao();
        home_dao_entities = home_dao_dao.getAllHome_Item();
        home_adapter = new Home_Rv_Adapter(getContext(), home_dao_entities);

        RecyclerView recyclerView = root.findViewById(R.id.home_rv);//将其实例化

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        //设置动画效果
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置适配器
        //添加默认的分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        recyclerView.setAdapter(home_adapter);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Toolbar toolbar = root.findViewById(R.id.toolbar_i_main);
        //添加菜单
        toolbar.inflateMenu(R.menu.home_menu_add);
        //监听菜单项
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                String msg = "";
                switch (menuItem.getItemId()) {
                    case R.id.home_menu_add:
                        getActivity().finish();
                        Intent shareIntent = new Intent(getContext(),Lnformation_add.class);
                        startActivity(shareIntent);
                        break;
                }
                if (!msg.equals("")) {
                    Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        btn_clear = root.findViewById(R.id.btn_home);
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View v) {
                home_dao_dao.deleteAllWords();
                home_dao_entities.clear();
                home_adapter.notifyDataSetChanged();

            }
        });
        //下拉刷新
        SwipeRefreshLayout swipeRefreshLayout = root.findViewById(R.id.swipe_refresh_layout);

        // 设置下拉刷新监听器
        swipeRefreshLayout.setColorSchemeResources(R.color.teal_200);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onRefresh() {
                // 执行下拉刷新操作

                swipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //关闭刷新
                        swipeRefreshLayout.setRefreshing(false);
                        home_adapter.notifyDataSetChanged();
                        Toast.makeText(getContext(), swipeRefreshLayout.isRefreshing() ? "正在刷新" : "刷新完成", Toast.LENGTH_SHORT).show();

                    }
                }, 1500);
                swipeRefreshLayout.setRefreshing(false);
            }

        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}