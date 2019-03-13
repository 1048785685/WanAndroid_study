package com.example.liuyang05_sx.androidstudy.ui.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liuyang05_sx.androidstudy.R;
import com.example.liuyang05_sx.androidstudy.base.fragment.BaseFragment;
import com.example.liuyang05_sx.androidstudy.bean.BaseResult;
import com.example.liuyang05_sx.androidstudy.bean.project.pro_Data;
import com.example.liuyang05_sx.androidstudy.bean.project.pro_Data_;
import com.example.liuyang05_sx.androidstudy.http.HttpHelperImp;
import com.example.liuyang05_sx.androidstudy.ui.main.activity.WebActivity;
import com.example.liuyang05_sx.androidstudy.utils.C;
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
        return view;
    }

    private void init() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL,false));
        adapter = new Project_Detail_Adapter(view.getContext(),mlist);
        recyclerView.setAdapter(adapter);
        adapter.setProClick(new Project_Detail_Adapter.ProjectClickListener() {
            @Override
            public void proItemLisenter(String title, String url) {
                Intent intent = new Intent();
                intent.putExtra("title",title);
                intent.putExtra("url",url);
                intent.putExtra("project","project");
                intent.setClass(view.getContext(),WebActivity.class);
                startActivity(intent);
            }
        });
        fragment_pro_refresh.setOnRefreshListener(new OnRefreshListener(){
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout){
                refreshLayout.finishRefresh(500);
            }
        });
        fragment_pro_refresh.setOnLoadMoreListener(new OnLoadMoreListener(){
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout){
                getDetailsData();
                refreshLayout.finishRefresh(1000);
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
                            mlist = pro_dataBaseResult.getData().getDatas();
                            mPage = pro_dataBaseResult.getData().getCurPage()+1;
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
        }else {
            adapter.addData(mlist);
        }
    }
}
