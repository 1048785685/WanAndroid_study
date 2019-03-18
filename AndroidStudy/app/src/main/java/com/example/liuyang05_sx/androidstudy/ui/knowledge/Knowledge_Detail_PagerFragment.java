package com.example.liuyang05_sx.androidstudy.ui.knowledge;

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

import com.example.liuyang05_sx.androidstudy.R;
import com.example.liuyang05_sx.androidstudy.base.fragment.BaseFragment;
import com.example.liuyang05_sx.androidstudy.bean.BaseResult;
import com.example.liuyang05_sx.androidstudy.bean.main.Data;
import com.example.liuyang05_sx.androidstudy.bean.main.Data_;
import com.example.liuyang05_sx.androidstudy.http.HttpHelperImp;
import com.example.liuyang05_sx.androidstudy.ui.knowledge.adapter.Knowledge_Detail_Adapter;
import com.example.liuyang05_sx.androidstudy.ui.main.activity.WebActivity;
import com.example.liuyang05_sx.androidstudy.utils.C;
import com.example.liuyang05_sx.androidstudy.utils.DividerItemDecoration;
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

public class Knowledge_Detail_PagerFragment extends BaseFragment {
    private View view;
    private int mPage = 0;
    private Knowledge_Detail_Adapter adapter;
    private boolean isFirst = true;
    private List<Data_> mlist = new ArrayList<>();
    @BindView(R.id.fragment_knowdetail_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.fragment_knowdetail_refresh)
    RefreshLayout fragment_wx_refresh;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_know_detail,container,false);
        ButterKnife.bind(this,view);
        getDetailsData();
        init();
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
                isFirst = true;
                mPage=0;
                getDetailsData();
                refreshLayout.finishRefresh(1000);
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
            public void onItemClick(String url, String title) {
                Intent intent = new Intent();
                intent.putExtra("title",title);
                intent.putExtra("url",url);
                intent.setClass(view.getContext(),WebActivity.class);
                startActivity(intent);
            }

            @Override
            public void onLikeClick(ImageView imageView, int id) {

            }
        });

    }

    public static Knowledge_Detail_PagerFragment getInstance(int id){
        Knowledge_Detail_PagerFragment fragment = new Knowledge_Detail_PagerFragment();
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
        HttpHelperImp.httpHelperImp.getKnowledgeDetailData(mPage,id)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResult<Data>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResult<Data> dataBaseResult) {
                        if (dataBaseResult.getErrorCode() == 0){
                            mlist = dataBaseResult.getData().getDatas();
                            mPage = dataBaseResult.getData().getCurPage();
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
}
