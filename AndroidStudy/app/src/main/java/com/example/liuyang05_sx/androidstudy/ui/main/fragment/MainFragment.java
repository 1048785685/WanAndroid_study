package com.example.liuyang05_sx.androidstudy.ui.main.fragment;

import android.content.Intent;
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
import com.example.liuyang05_sx.androidstudy.bean.main.Data_;
import com.example.liuyang05_sx.androidstudy.bean.main.Main_Banner;
import com.example.liuyang05_sx.androidstudy.ui.main.activity.WebActivity;
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
    private int page = 0;
    private View view;
    private BannerPresenter presenter;
    private MainRecyclerAdapter mainRecyclerAdapter;
    private int flag = 0;
    private List<Main_Banner> Banner_list = new ArrayList<>();
    private List<Data_> Main_list = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main,container,false);
        ButterKnife.bind(this,view);
        main_recyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(),DividerItemDecoration.VERTICAL_LIST));
        main_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL,false));

        main_recyclerView.setVisibility(View.INVISIBLE);
        presenter = new BannerPresenter();
        presenter.attachView(this);
        presenter.getData(page);
        return view;
    }

    private void initRecyclerView(){
        main_recyclerView.setVisibility(View.VISIBLE);
        mainRecyclerAdapter = new MainRecyclerAdapter(view.getContext(),Banner_list,Main_list);
        main_recyclerView.setAdapter(mainRecyclerAdapter);
        mainRecyclerAdapter.setOnRecycleViewListener(new MainRecyclerAdapter.OnRecyclerViewListener() {
            @Override
            public void onItemClick(String url,String title) {
                Intent intent = new Intent();
                intent.putExtra("title",title);
                intent.putExtra("url",url);
                intent.setClass(view.getContext(),WebActivity.class);
                startActivity(intent);
            }

            @Override
            public void onLikeClick(View view) {
                Toast.makeText(view.getContext() ,"点击收藏按钮",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onBannerClick(String url,String title) {
                Intent intent = new Intent();
                intent.putExtra("title",title);
                intent.putExtra("url",url);
                intent.setClass(view.getContext(),WebActivity.class);
                startActivity(intent);
            }
        });

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page=0;
                presenter.getData(page);
                refreshLayout.finishRefresh(1000,true/*,false*/);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener(){
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout){
                presenter.loadMore(page);
                refreshLayout.finishLoadMore(1000/*,false*/);
            }
        });
    }

    @Override
    public void showDataView(int page, List<Main_Banner> list,List<Data_> Main_list) {
        if (flag==0){
            this.page = page;
            Banner_list = list;
            this.Main_list = Main_list;
            flag++;
            initRecyclerView();
        }else {
            mainRecyclerAdapter.replaceManiData(Main_list);
        }
        this.page =page;
    }

    @Override
    public void showLoadMore(int page, List<Data_> list) {
        mainRecyclerAdapter.addData(list);
        this.page = page;
    }


}
