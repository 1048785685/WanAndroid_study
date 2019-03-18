package com.example.liuyang05_sx.androidstudy.ui.knowledge;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.liuyang05_sx.androidstudy.R;
import com.example.liuyang05_sx.androidstudy.base.BaseActivity;
import com.example.liuyang05_sx.androidstudy.base.fragment.BaseFragment;
import com.example.liuyang05_sx.androidstudy.bean.knowledge.Child;
import com.example.liuyang05_sx.androidstudy.bean.knowledge.Datum;

import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Knowledge_Detail_Activity extends BaseActivity {
    @BindView(R.id.knowledge_detail_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.knowledge_detail_viewpager)
    ViewPager mViewPager;
    @BindView(R.id.knowledge_detail_tab_layout)
    SlidingTabLayout mTabLayout;

    private Datum datum;
    private List<BaseFragment> fragments = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowledge_detail);
        ButterKnife.bind(this);
        init();
        showTabData();
    }

    private void init() {
        datum = (Datum) getIntent().getSerializableExtra("list");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(datum.getName());
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void init(List<Child> list){
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragments.get(i);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return list.get(position).getName();
            }
        });
        mTabLayout.setViewPager(mViewPager);
        mTabLayout.setVisibility(View.VISIBLE);
    }
    private void showTabData() {
        for (Child child: datum.getChildren()) {
            fragments.add(Knowledge_Detail_PagerFragment.getInstance(child.getId()));
        }
        init(datum.getChildren());
    }
}
