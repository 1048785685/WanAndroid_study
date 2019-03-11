package com.example.liuyang05_sx.androidstudy.ui.project;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liuyang05_sx.androidstudy.R;
import com.example.liuyang05_sx.androidstudy.base.fragment.BaseFragment;
import com.example.liuyang05_sx.androidstudy.bean.Wx_and_Pro.Wx_pro_article;
import com.example.liuyang05_sx.androidstudy.ui.project.presenter.Project_P;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Project_fragment extends BaseFragment implements IPro_View {
    private View view;
    private Project_P presenter;
    private List<BaseFragment> fragments = new ArrayList<>();
    @BindView(R.id.fragment_project_tab)
    SlidingTabLayout mTabLayout;
    @BindView(R.id.project_viewpager)
    ViewPager viewPager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_project,container,false);
        ButterKnife.bind(this,view);
        presenter = new Project_P();
        presenter.attachView(this);
        presenter.getData();
        return view;
    }

    private void init(List<Wx_pro_article> list){
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
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
        mTabLayout.setViewPager(viewPager);
        mTabLayout.setVisibility(View.VISIBLE);
    }



    @Override
    public void showProjectData(List<Wx_pro_article> list) {
        for (Wx_pro_article article: list) {
            fragments.add(Project_Detail_Fragment.getInstance(article.getId()));
        }
        init(list);
    }
}
