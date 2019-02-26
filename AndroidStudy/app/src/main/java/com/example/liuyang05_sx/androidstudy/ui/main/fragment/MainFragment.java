package com.example.liuyang05_sx.androidstudy.ui.main.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.liuyang05_sx.androidstudy.R;
import com.example.liuyang05_sx.androidstudy.base.fragment.BaseFragment;
import com.example.liuyang05_sx.androidstudy.bean.main.Main_Banner;
import com.example.liuyang05_sx.androidstudy.ui.main.adapter.MainRecyclerAdapter;
import com.example.liuyang05_sx.androidstudy.ui.main.presenter.BannerPresenter;
import com.example.liuyang05_sx.androidstudy.utils.DividerItemDecoration;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainFragment extends BaseFragment implements IBannerView{
    @BindView(R.id.main_pager_recycler_view)
    RecyclerView main_recyclerView;
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;

    private View view;
    private BannerPresenter presenter;
    private MainRecyclerAdapter mainRecyclerAdapter;
    private List<Main_Banner> mData = new ArrayList<Main_Banner>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main,container,false);
        ButterKnife.bind(this,view);
        initRecyclerView();
        presenter = new BannerPresenter();
        presenter.attachView(this);
        Log.d("XXX","attachView");
        presenter.getData();
        return view;
    }



    private void initRecyclerView(){
        main_recyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(),DividerItemDecoration.VERTICAL_LIST));
        main_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL,false));

        mainRecyclerAdapter = new MainRecyclerAdapter(view.getContext(),mData);
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
//                presenter.getData();
                refreshLayout.finishRefresh(1000,true/*,false*/);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(1000/*,false*/);
            }
        });
    }

    @Override
    public void showDataView(List<Main_Banner> list) {
        mainRecyclerAdapter.replaceData(list);
        main_recyclerView.setAdapter(mainRecyclerAdapter);
    }

    @Override
    public void refresh(List<Main_Banner> list) {

    }
}
