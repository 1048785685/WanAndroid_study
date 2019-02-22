package com.example.liuyang05_sx.androidstudy.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;

import com.example.liuyang05_sx.androidstudy.R;
import com.example.liuyang05_sx.androidstudy.base.BaseActivity;
import com.example.liuyang05_sx.androidstudy.ui.fragment.MainFragment;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    private FragmentManager fragmentManager;
    private Fragment currentFragment = new Fragment();
    private MainFragment mainFragment = new MainFragment();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFrag();
    }



    private void initFrag() {
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        currentFragment = mainFragment;
        transaction.add(R.id.fragment,mainFragment).show(mainFragment).commit();

    }
}
