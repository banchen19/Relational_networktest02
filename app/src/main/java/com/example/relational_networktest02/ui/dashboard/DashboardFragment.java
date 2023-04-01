package com.example.relational_networktest02.ui.dashboard;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.relational_networktest02.R;
import com.example.relational_networktest02.databinding.FragmentDashboardBinding;
import com.example.relational_networktest02.ui.home.Home_in_add.ui.Lnformation_add;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Toolbar toolbar = root.findViewById(R.id.toolbar_i_main_b);
        //添加菜单
        toolbar.inflateMenu(R.menu.home_menu_add);
        //监听菜单项
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                String msg = "";
                switch (menuItem.getItemId()) {
                    case R.id.home_menu_add:
                        Toast.makeText(getContext(), "添加好友", Toast.LENGTH_SHORT).show();
                        break;
                }
                if (!msg.equals("")) {
                    Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}