package com.example.liuyang05_sx.androidstudy.ui.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liuyang05_sx.androidstudy.R;
import com.example.liuyang05_sx.androidstudy.base.fragment.BaseFragment;
import com.example.liuyang05_sx.androidstudy.bean.navigation.NavData;
import com.example.liuyang05_sx.androidstudy.ui.main.activity.WebActivity;
import com.example.liuyang05_sx.androidstudy.ui.navigation.adapter.Nav_Recycler_Adapter;
import com.example.liuyang05_sx.androidstudy.ui.navigation.presenter.Nav_Presenter;
import com.example.liuyang05_sx.androidstudy.utils.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;

public class NavigationFragment extends BaseFragment implements INavi_View{
    private View view;
    private Nav_Presenter presenter;
    private Nav_Recycler_Adapter adapter;
    private List<NavData> list = new ArrayList<>();
    @BindView(R.id.navigation_recycler)
    RecyclerView navigation_recycler;
    @BindView(R.id.navigation_TabLayout)
    VerticalTabLayout navigation_TabLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_navigation,container,false);
        ButterKnife.bind(this,view);
        initView();
        presenter = new Nav_Presenter();
        presenter.attachView(this);
        presenter.getNavData();
        return view;
    }

    private void initView() {
        navigation_recycler.addItemDecoration(new DividerItemDecoration(view.getContext(),DividerItemDecoration.VERTICAL_LIST));
        navigation_recycler.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL,false));
        adapter = new Nav_Recycler_Adapter(view.getContext(),list);
        navigation_recycler.setAdapter(adapter);
        adapter.setOnTagClick(new Nav_Recycler_Adapter.OnTagCListener() {
            @Override
            public void onTagClickEvent(int position, int i) {
                Intent intent = new Intent();
                intent.putExtra("title",list.get(position).getList().get(i).getTitle());
                intent.putExtra("url",list.get(position).getList().get(i).getLink());
                intent.setClass(view.getContext(),WebActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void putNavData(List<NavData> list) {
        this.list = list;
        navigation_TabLayout.setTabAdapter(new TabAdapter() {
            @Override
            public int getCount() {
                return list == null ? 0 : list.size();
            }

            @Override
            public ITabView.TabBadge getBadge(int position) {
                return null;
            }

            @Override
            public ITabView.TabIcon getIcon(int position) {
                return null;
            }

            @Override
            public ITabView.TabTitle getTitle(int position) {
                return new TabView.TabTitle.Builder()
                        .setContent(list.get(position).getName())
                        .setTextColor(ContextCompat.getColor(view.getContext(),R.color.blue),
                                ContextCompat.getColor(view.getContext(),R.color.white))
                        .build();
            }

            @Override
            public int getBackground(int position) {
                return -1;
            }
        });
        adapter.changedData(list);
        navigation_TabLayout.setVisibility(View.VISIBLE);
        navigation_recycler.setVisibility(View.VISIBLE);
    }
}
