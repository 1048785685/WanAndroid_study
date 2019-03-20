package com.example.liuyang05_sx.androidstudy.ui.project;

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
import android.widget.Toast;

import com.example.liuyang05_sx.androidstudy.R;
import com.example.liuyang05_sx.androidstudy.base.fragment.BaseFragment;
import com.example.liuyang05_sx.androidstudy.bean.BaseResult;
import com.example.liuyang05_sx.androidstudy.bean.event.CollectEvent;
import com.example.liuyang05_sx.androidstudy.bean.event.LoginEvent;
import com.example.liuyang05_sx.androidstudy.bean.project.pro_Data;
import com.example.liuyang05_sx.androidstudy.bean.project.pro_Data_;
import com.example.liuyang05_sx.androidstudy.http.HttpHelperImp;
import com.example.liuyang05_sx.androidstudy.ui.main.activity.LoginActivity;
import com.example.liuyang05_sx.androidstudy.ui.main.activity.WebActivity;
import com.example.liuyang05_sx.androidstudy.utils.C;
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

public class Project_Detail_Fragment extends BaseFragment {
    private View view;
    private int mPage = 1;
    private Project_Detail_Adapter adapter;
    private boolean isFirst = true;
    private List<pro_Data_> mlist = new ArrayList<>();
    @BindView(R.id.project_detail_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.fragment_pro_refresh)
    RefreshLayout fragment_pro_refresh;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_pro_detail,container,false);
        ButterKnife.bind(this,view);
        getDetailsData();
        init();
        registerLoginEvent();
        return view;
    }

    private void init() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL,false));
        adapter = new Project_Detail_Adapter(view.getContext(),mlist);
        recyclerView.setAdapter(adapter);
        adapter.setProClick(new Project_Detail_Adapter.ProjectClickListener() {
            @Override
            public void proItemLisenter(int position,boolean like) {
                Intent intent = new Intent();
                intent.putExtra("title",mlist.get(position).getTitle());
                intent.putExtra("url", mlist.get(position).getLink());
                intent.putExtra("id", mlist.get(position).getId());
                intent.putExtra("like",like);
                intent.setClass(view.getContext(),WebActivity.class);
                startActivity(intent);
            }
            @Override
            public void proLike(int id, boolean isLike) {
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

        fragment_pro_refresh.setOnRefreshListener(new OnRefreshListener(){
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout){
                mPage=1;
                isFirst=true;
                getDetailsData();
                refreshLayout.finishRefresh(1000,true);
            }
        });
        fragment_pro_refresh.setOnLoadMoreListener(new OnLoadMoreListener(){
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout){
                getDetailsData();
                refreshLayout.finishLoadMore(1000);
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


    @SuppressLint("CheckResult")
    private void registerLoginEvent() {

        RxBus.getDefault().toObservable(this,LoginEvent.class).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LoginEvent>() {
                    @Override
                    public void accept(LoginEvent loginEvent) throws Exception {

                        fragment_pro_refresh.autoRefresh();
                    }
                });
        RxBus.getDefault().toObservable(this,CollectEvent.class).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CollectEvent>() {
                    @Override
                    public void accept(CollectEvent collectEvent) throws Exception {
                        fragment_pro_refresh.autoRefresh();
                    }
                });
    }

    public static Project_Detail_Fragment getInstance(int id){
        Project_Detail_Fragment fragment = new Project_Detail_Fragment();
        Bundle args = new Bundle();
        args.putInt(C.ARG_PARAM1, id);
        fragment.setArguments(args);
        return fragment;
    }

    private void getDetailsData(){
        int id = getArguments().getInt(C.ARG_PARAM1);
        HttpHelperImp.httpHelperImp.getProjectDetailData(mPage,id)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResult<pro_Data>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResult<pro_Data> pro_dataBaseResult) {
                        if (pro_dataBaseResult.getErrorCode() == 0){
                            mPage = pro_dataBaseResult.getData().getCurPage()+1;
                            mlist = pro_dataBaseResult.getData().getDatas();
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

    private void showRecyclerView() {
        if (isFirst){
            adapter.replaceManiData(mlist);
            isFirst = false;
        }
        else {
            adapter.addData(mlist);
        }
    }


}
