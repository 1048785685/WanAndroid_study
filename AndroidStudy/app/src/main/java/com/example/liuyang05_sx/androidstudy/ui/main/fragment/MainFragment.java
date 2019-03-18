package com.example.liuyang05_sx.androidstudy.ui.main.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.liuyang05_sx.androidstudy.R;
import com.example.liuyang05_sx.androidstudy.base.fragment.BaseFragment;
import com.example.liuyang05_sx.androidstudy.bean.event.CollectEvent;
import com.example.liuyang05_sx.androidstudy.bean.event.LoginEvent;
import com.example.liuyang05_sx.androidstudy.bean.main.Data_;
import com.example.liuyang05_sx.androidstudy.bean.main.Main_Banner;
import com.example.liuyang05_sx.androidstudy.ui.main.activity.LoginActivity;
import com.example.liuyang05_sx.androidstudy.ui.main.activity.WebActivity;
import com.example.liuyang05_sx.androidstudy.ui.main.adapter.MainRecyclerAdapter;
import com.example.liuyang05_sx.androidstudy.ui.main.presenter.BannerPresenter;
import com.example.liuyang05_sx.androidstudy.utils.C;
import com.example.liuyang05_sx.androidstudy.utils.DividerItemDecoration;

import com.example.liuyang05_sx.androidstudy.utils.RxBus;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.functions.Consumer;


public class MainFragment extends BaseFragment implements IBannerView{
    @BindView(R.id.main_pager_recycler_view)
    RecyclerView main_recyclerView;
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    private int page = 0;
    private View mview;
    private BannerPresenter presenter;
    private MainRecyclerAdapter mainRecyclerAdapter;
    private int flag = 0;
    private List<Main_Banner> Banner_list = new ArrayList<>();
    private List<Data_> Main_list = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mview = inflater.inflate(R.layout.fragment_main,container,false);
        ButterKnife.bind(this,mview);
        main_recyclerView.addItemDecoration(new DividerItemDecoration(mview.getContext(),DividerItemDecoration.VERTICAL_LIST));
        main_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL,false));

        main_recyclerView.setVisibility(View.INVISIBLE);
        presenter = new BannerPresenter();
        presenter.attachView(this);
        presenter.getData(page);
        registerLoginEvent();
        return mview;
    }

    private void initRecyclerView(){
        main_recyclerView.setVisibility(View.VISIBLE);
        mainRecyclerAdapter = new MainRecyclerAdapter(mview.getContext(),Banner_list,Main_list,true);
        main_recyclerView.setAdapter(mainRecyclerAdapter);
        mainRecyclerAdapter.setOnRecycleViewListener(new MainRecyclerAdapter.OnRecyclerViewListener() {
            @Override
            public void onItemClick(String url,String title) {
                Intent intent = new Intent();
                intent.putExtra("title",title);
                intent.putExtra("url",url);
                intent.setClass(mview.getContext(),WebActivity.class);
                startActivity(intent);
            }

            @Override
            public void onLikeClick(ImageView imageView,int id) {
                if (C.isLogin) {
                    presenter.Save(id);
                    imageView.setImageResource(R.drawable.icon_like_selected);
                }else {
                    Intent intent = new Intent();
                    intent.setClass(mview.getContext(),LoginActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onBannerClick(String url,String title) {
                Intent intent = new Intent();
                intent.putExtra("title",title);
                intent.putExtra("url",url);
                intent.setClass(mview.getContext(),WebActivity.class);
                startActivity(intent);
            }
        });

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page=0;
                presenter.getData(page);
                refreshLayout.finishRefresh(1000,true);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener(){
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout){
                presenter.loadMore(page);
                refreshLayout.finishLoadMore(1000);
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
            mainRecyclerAdapter.replaceMainData(Main_list);
        }
        this.page =page;
    }

    @Override
    public void showLoadMore(int page, List<Data_> list) {
        mainRecyclerAdapter.addData(list);
        this.page = page;
    }

    @Override
    public void savesuccess() {
        RxBus.getDefault().post(new CollectEvent());
        Toast.makeText(mview.getContext() ,"文章已收藏",Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("CheckResult")
    private void registerLoginEvent() {

        RxBus.getDefault().toObservable(this,LoginEvent.class).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LoginEvent>() {
                    @Override
                    public void accept(LoginEvent loginEvent) throws Exception {

                        refreshLayout.autoRefresh();
                    }
                });
        RxBus.getDefault().toObservable(this,CollectEvent.class).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CollectEvent>() {
                    @Override
                    public void accept(CollectEvent collectEvent) throws Exception {

                        refreshLayout.autoRefresh();
                    }
                });
    }

}
