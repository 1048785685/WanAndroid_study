package com.example.liuyang05_sx.androidstudy.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.liuyang05_sx.androidstudy.R;
import com.example.liuyang05_sx.androidstudy.base.fragment.BaseFragment;
import com.example.liuyang05_sx.androidstudy.ui.adapter.MainRecyclerAdapter;
import com.example.liuyang05_sx.androidstudy.utils.DividerItemDecoration;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainFragment extends BaseFragment {
    @BindView(R.id.main_pager_recycler_view)
    RecyclerView main_recyclerView;
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    private View view;
    private MainRecyclerAdapter mainRecyclerAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main,container,false);
        ButterKnife.bind(this,view);
        initRecyclerView();
        return view;
    }

    private void initRecyclerView(){
        main_recyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(),DividerItemDecoration.VERTICAL_LIST));
        main_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL,false));
        mainRecyclerAdapter = new MainRecyclerAdapter(view.getContext());
        main_recyclerView.setAdapter(mainRecyclerAdapter);
        main_recyclerView.setVisibility(View.VISIBLE);
        mainRecyclerAdapter.setOnRecycleViewListener(new MainRecyclerAdapter.OnRecyclerViewListener() {
            @Override
            public void onItemClick(View view) {
                Toast.makeText(view.getContext(),"点击Item按钮",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLikeClick(View view) {
                Toast.makeText(view.getContext() ,"点击收藏按钮",Toast.LENGTH_SHORT).show();
            }
        });
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(1000/*,false*/);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(1000/*,false*/);
            }
        });
    }
}
