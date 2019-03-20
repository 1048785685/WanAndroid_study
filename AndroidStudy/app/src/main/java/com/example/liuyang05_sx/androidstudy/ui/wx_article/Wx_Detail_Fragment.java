package com.example.liuyang05_sx.androidstudy.ui.wx_article;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.liuyang05_sx.androidstudy.R;
import com.example.liuyang05_sx.androidstudy.base.fragment.BaseFragment;
import com.example.liuyang05_sx.androidstudy.bean.BaseResult;
import com.example.liuyang05_sx.androidstudy.bean.event.CollectEvent;
import com.example.liuyang05_sx.androidstudy.bean.event.LoginEvent;
import com.example.liuyang05_sx.androidstudy.bean.main.Data;
import com.example.liuyang05_sx.androidstudy.bean.main.Data_;
import com.example.liuyang05_sx.androidstudy.http.HttpHelperImp;
import com.example.liuyang05_sx.androidstudy.ui.knowledge.adapter.Knowledge_Detail_Adapter;
import com.example.liuyang05_sx.androidstudy.ui.main.activity.LoginActivity;
import com.example.liuyang05_sx.androidstudy.ui.main.activity.WebActivity;
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
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class Wx_Detail_Fragment extends BaseFragment {
    private View view;
    private int mPage = 1;
    private Knowledge_Detail_Adapter adapter;
    private boolean isFirst = true;
    private List<Data_> mlist = new ArrayList<>();
    @BindView(R.id.wx_detail_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.fragment_wx_refresh)
    RefreshLayout fragment_wx_refresh;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_wx_detail,container,false);
        ButterKnife.bind(this,view);
        getDetailsData();
        init();
        registerLoginEvent();
        return view;
    }

    private void init() {
        recyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(),DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL,false));
        adapter = new Knowledge_Detail_Adapter(view.getContext(),mlist);
        recyclerView.setAdapter(adapter);
        fragment_wx_refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPage=1;
                isFirst=true;
                getDetailsData();
                refreshLayout.finishRefresh(1000,true);
            }
        });
        fragment_wx_refresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                getDetailsData();
                refreshLayout.finishLoadMore(1000);
            }
        });

        adapter.setOnRecycleViewListener(new Knowledge_Detail_Adapter.OnRecyclerViewListener() {
            @Override
            public void onItemClick(int position,boolean isLike) {
                Intent intent = new Intent();
                intent.putExtra("title",mlist.get(position).getTitle());
                intent.putExtra("url",mlist.get(position).getLink());
                intent.putExtra("id",mlist.get(position).getId());
                intent.putExtra("like",isLike);
                intent.setClass(view.getContext(),WebActivity.class);
                startActivity(intent);
            }

            @Override
            public void onLikeClick(int id,boolean isLike) {
                if (C.isLogin&&!isLike) {
                    Save(id);
                }else if (C.isLogin&&isLike){
                    unCollect(id);
                }else {
                    Intent intent = new Intent();
                    intent.setClass(view.getContext(),LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    public static Wx_Detail_Fragment getInstance(int id){
        Wx_Detail_Fragment fragment = new Wx_Detail_Fragment();
        Bundle args = new Bundle();
        args.putInt(C.ARG_PARAM1, id);
        fragment.setArguments(args);
        return fragment;
    }

    private void showRecyclerView(){
        if (isFirst){
        adapter.replaceMainData(mlist);
        isFirst = false;
        }else {
            adapter.addData(mlist);
        }
    }
    private void getDetailsData(){
        int id = getArguments().getInt(C.ARG_PARAM1);
        HttpHelperImp.httpHelperImp.getWxDetailData(id,mPage)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResult<Data>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResult<Data> dataBaseResult) {
                        if (dataBaseResult.getErrorCode() == 0){
                            mlist = dataBaseResult.getData().getDatas();
                            mPage = dataBaseResult.getData().getCurPage()+1;
                            showRecyclerView();
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
    @SuppressLint("CheckResult")
    private void registerLoginEvent() {

        RxBus.getDefault().toObservable(this,LoginEvent.class).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LoginEvent>() {
                    @Override
                    public void accept(LoginEvent loginEvent) throws Exception {

                        fragment_wx_refresh.autoRefresh();
                    }
                });
        RxBus.getDefault().toObservable(this,CollectEvent.class).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CollectEvent>() {
                    @Override
                    public void accept(CollectEvent collectEvent) throws Exception {
                        fragment_wx_refresh.autoRefresh();
                    }
                });
    }
    private void Save(int id) {
        HttpHelperImp.httpHelperImp.SaveArticle(id)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResult baseResult) {
                        RxBus.getDefault().post(new CollectEvent());
                        Toast.makeText(view.getContext() ,"文章已收藏",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void unCollect(int id) {
        HttpHelperImp.httpHelperImp.unCollect(id)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResult baseResult) {
                        RxBus.getDefault().post(new CollectEvent());
                        Toast.makeText(view.getContext() ,"取消收藏成功",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
