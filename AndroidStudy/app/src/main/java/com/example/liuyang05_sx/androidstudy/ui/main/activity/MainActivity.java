package com.example.liuyang05_sx.androidstudy.ui.main.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.liuyang05_sx.androidstudy.R;
import com.example.liuyang05_sx.androidstudy.base.BaseActivity;
import com.example.liuyang05_sx.androidstudy.base.fragment.BaseFragment;
import com.example.liuyang05_sx.androidstudy.ui.knowledge.Knowledge_Fragment;
import com.example.liuyang05_sx.androidstudy.ui.main.fragment.MainFragment;
import com.example.liuyang05_sx.androidstudy.utils.BottomNavigationViewHelper;
import com.example.liuyang05_sx.androidstudy.utils.StatusBarUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    private ArrayList<BaseFragment> mFragments = new ArrayList<>();
    private Fragment currentFragment = new Fragment();
    private MainFragment mainFragment = new MainFragment();
    private Knowledge_Fragment knowledge_fragment = new Knowledge_Fragment();

    @BindView(R.id.main_bottom)
    BottomNavigationView mBottomNavigationView;
    @BindView(R.id.common_title)
    TextView mCommon_title;
    @BindView(R.id.nav_view)
    NavigationView mNav_view;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.common_search)
    TextView mSearch;
    @BindView(R.id.mdrawerLayout)
    DrawerLayout mDrawerLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initBottomNavigationView();
        initFragment();
        initNavgationView();
    }

    private void initNavgationView() {
        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("xxx","dianji search");
            }
        });


        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        StatusBarUtil.setStatusColor(getWindow(), ContextCompat.getColor(this, R.color.main_status_bar_blue), 1f);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(GravityCompat.START);

            }
        });



        TextView textView = mNav_view.getHeaderView(0).findViewById(R.id.login_in);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        mNav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_item_Study:
                        menuItem.setChecked(true);
                        Log.d("xxx","study");
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_item_collect:
                        menuItem.setChecked(true);
                        Log.d("xxx","collect");
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_item_setting:
                        menuItem.setChecked(true);
                        Log.d("xxx","setting");
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_item_about:
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        Log.d("xxx","about");
                        break;
                }

                return true;
            }
        });
    }

    private void switchFragment(String title,int position){
        mCommon_title.setText(title);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment targetFrag = mFragments.get(position);
        if (!targetFrag.isAdded()){
            transaction.add(R.id.fragment,targetFrag);
        }
        if (currentFragment!=null){
            transaction.hide(currentFragment);
        }
        currentFragment = targetFrag;
        transaction.show(targetFrag).commit();
    }


    private void initBottomNavigationView() {
        BottomNavigationViewHelper.removeShiftMode(mBottomNavigationView);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.tab_main:
                        switchFragment("首页",0);
                        break;
                    case R.id.tab_knowledge:
                        switchFragment("知识体系",1);
                        break;
                }
                return true;
            }
        });
    }

    private void initFragment() {
        mFragments.add(mainFragment);
        mFragments.add(knowledge_fragment);
        switchFragment("首页",0);
    }
}
