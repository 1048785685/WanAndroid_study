package com.example.liuyang05_sx.androidstudy.ui.wx_article;

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
import com.example.liuyang05_sx.androidstudy.ui.wx_article.presenter.Wx_P;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Wx_Fragment extends BaseFragment implements IWx_Fragment{
    @BindView(R.id.fragment_wx_tab)
    SlidingTabLayout mTabLayout;
    @BindView(R.id.wx_viewpager)
    ViewPager viewPager;
    private View view;
    private Wx_P presenter;
    private List<BaseFragment> fragments = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_wxarticle,container,false);
        ButterKnife.bind(this,view);
        presenter = new Wx_P();
        presenter.attachView(this);
        presenter.getTabData();
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
    public void showTabData(List<Wx_pro_article> list){
        for (Wx_pro_article article: list) {
            fragments.add(Wx_Detail_Fragment.getInstance(article.getId()));
        }
        init(list);
    }
}
