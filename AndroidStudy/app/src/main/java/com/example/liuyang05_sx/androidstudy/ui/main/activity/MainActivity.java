package com.example.liuyang05_sx.androidstudy.ui.main.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.liuyang05_sx.androidstudy.R;
import com.example.liuyang05_sx.androidstudy.base.BaseActivity;
import com.example.liuyang05_sx.androidstudy.base.fragment.BaseFragment;
import com.example.liuyang05_sx.androidstudy.bean.BaseResult;
import com.example.liuyang05_sx.androidstudy.bean.event.LoginEvent;
import com.example.liuyang05_sx.androidstudy.http.HttpHelperImp;
import com.example.liuyang05_sx.androidstudy.ui.knowledge.Knowledge_Fragment;
import com.example.liuyang05_sx.androidstudy.ui.main.fragment.MainFragment;
import com.example.liuyang05_sx.androidstudy.ui.navigation.NavigationFragment;
import com.example.liuyang05_sx.androidstudy.ui.project.Project_fragment;
import com.example.liuyang05_sx.androidstudy.ui.save.CollectActivity;
import com.example.liuyang05_sx.androidstudy.ui.wx_article.Wx_Fragment;
import com.example.liuyang05_sx.androidstudy.utils.ACache;
import com.example.liuyang05_sx.androidstudy.utils.BottomNavigationViewHelper;
import com.example.liuyang05_sx.androidstudy.utils.C;
import com.example.liuyang05_sx.androidstudy.utils.RxBus;
import com.example.liuyang05_sx.androidstudy.utils.StatusBarUtil;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    private ArrayList<BaseFragment> mFragments = new ArrayList<>();
    private Fragment currentFragment = new Fragment();
    private MainFragment mainFragment = new MainFragment();
    private NavigationFragment navigationFragment = new NavigationFragment();
    private Wx_Fragment wx_fragment = new Wx_Fragment();
    private Knowledge_Fragment knowledge_fragment = new Knowledge_Fragment();
    private Project_fragment project_fragment = new Project_fragment();
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

    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        registerEvent();
        initBottomNavigationView();

        initFragment();
        initNavgationView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @SuppressLint("CheckResult")
    private void registerEvent() {

        RxBus.getDefault().toObservable(this,LoginEvent.class).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LoginEvent>() {
                    @Override
                    public void accept(LoginEvent loginEvent) throws Exception {
                        if (loginEvent.isLogin()) {
                            textView.setText(loginEvent.getUsername());
                            textView.setClickable(false);
                            mNav_view.getMenu().getItem(4).setVisible(true);
                        }else if (!loginEvent.isLogin()){
                            textView.setText("登录");
                            textView.setClickable(true);
                            mNav_view.getMenu().getItem(4).setVisible(false);
//                            switchFragment("首页",0);
                        }
                    }
                });
    }

    private void initNavgationView() {
        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,SearchActivity.class);
                startActivity(intent);
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



        textView = mNav_view.getHeaderView(0).findViewById(R.id.login_in);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        MenuItem nightModeItem = mNav_view.getMenu().findItem(R.id.nav_item_night);
        if (C.isNight) {
            nightModeItem.setIcon(R.drawable.ic_day);
            nightModeItem.setTitle("日间模式");
        } else {
            nightModeItem.setIcon(R.drawable.ic_night);
            nightModeItem.setTitle("夜间模式");
        }
        mNav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_item_Study:
                        menuItem.setChecked(true);
                        switchFragment("首页",0);

                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_item_collect:
                        menuItem.setCheckable(false);


                        if (C.isLogin){
                            Intent intent = new Intent();
                            intent.setClass(MainActivity.this,CollectActivity.class);
                            startActivity(intent);
                        }else {
                            Intent intent = new Intent();
                            intent.setClass(MainActivity.this,LoginActivity.class);
                            startActivity(intent);
                        }
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_item_night:
                        menuItem.setCheckable(false);
                        if (C.isNight) {
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                            C.isNight=false;
                            menuItem.setTitle("日间模式");
                        } else {
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                            C.isNight=true;
                            menuItem.setTitle("夜间模式");
                        }
                        Recreate();
                        break;
                    case R.id.nav_item_about:
                        menuItem.setCheckable(false);

                        Intent intent = new Intent();
                        intent.setClass(MainActivity.this,AboutActivity.class);
                        startActivity(intent);
                        mDrawerLayout.closeDrawers();

                        break;
                    case R.id.nav_item_loginout:
                        Login_out();
                        break;
                }

                return true;
            }
        });
    }

    private void Recreate() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (mainFragment!=null){
            transaction.remove(mainFragment);
        }
        if (knowledge_fragment!=null){
            transaction.remove(knowledge_fragment);
        }
        if (wx_fragment!=null){
            transaction.remove(wx_fragment);
        }
        if (navigationFragment!=null){
            transaction.remove(navigationFragment);
        }
        if (project_fragment!=null){
            transaction.remove(project_fragment);
        }
        transaction.commitAllowingStateLoss();
        recreate();
    }

    public void Login_out(){
        HttpHelperImp.httpHelperImp.Login_out()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResult baseResult) {
                        if (baseResult.getErrorCode()==0){
                        RxBus.getDefault().post(new LoginEvent(false,""));
                        ACache.get(MainActivity.this).put(C.UserName,"");
                        ACache.get(MainActivity.this).put(C.PassWord,"");
                        C.isLogin=false;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    private void switchFragment(String title,int position){
        if (position>4){
            mBottomNavigationView.setVisibility(View.INVISIBLE);
        }else {
            mBottomNavigationView.setVisibility(View.VISIBLE);
        }
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
                    case R.id.tab_wx_article:

                        switchFragment("公众号",2);
                        break;
                    case R.id.tab_navigation:

                        switchFragment("导航",3);
                        break;
                    case R.id.tab_project:

                        switchFragment("项目",4);
                        break;
                }
                return true;
            }
        });
    }



    private void initFragment() {
        mFragments.add(mainFragment);
        mFragments.add(knowledge_fragment);
        mFragments.add(wx_fragment);
        mFragments.add(navigationFragment);
        mFragments.add(project_fragment);

        switchFragment("首页",0);
    }
}
