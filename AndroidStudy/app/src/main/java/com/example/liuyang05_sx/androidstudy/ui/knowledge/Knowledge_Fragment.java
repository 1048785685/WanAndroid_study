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
import android.widget.Toast;

import com.example.liuyang05_sx.androidstudy.R;
import com.example.liuyang05_sx.androidstudy.base.fragment.BaseFragment;
import com.example.liuyang05_sx.androidstudy.bean.knowledge.Datum;
import com.example.liuyang05_sx.androidstudy.ui.knowledge.adapter.Knowledge_RecyclerAdapter;
import com.example.liuyang05_sx.androidstudy.ui.knowledge.presenter.Know_Presenter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Knowledge_Fragment extends BaseFragment implements IKnow_View{
    @BindView(R.id.knowledge_recycler)
    RecyclerView knowledge_recyclerView;
    @BindView(R.id.know_refresh)
    RefreshLayout know_refresh;
    private View view;
    private Knowledge_RecyclerAdapter mKnowledge_adapter;
    private Know_Presenter presenter;
    private List<Datum> datumList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_knowledge,container,false);
        ButterKnife.bind(this,view);
        initRecyclerView();
        presenter = new Know_Presenter();
        presenter.attachView(this);
        presenter.getKnowData();
        return view;
    }

    private void initRecyclerView() {
        knowledge_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL,false));

        mKnowledge_adapter = new Knowledge_RecyclerAdapter(view.getContext(),datumList);
        knowledge_recyclerView.setAdapter(mKnowledge_adapter);

        mKnowledge_adapter.setOnRecyclerListener(new Knowledge_RecyclerAdapter.OnRecyclerListener() {
            @Override
            public void onItemListener(int position) {
                Intent intent = new Intent();
                Bundle bundle =new Bundle();
                bundle.putSerializable("list",datumList.get(position));
                intent.putExtras(bundle);
                intent.setClass(view.getContext(),Knowledge_Detail_Activity.class);
                startActivity(intent);
            }
        });

        know_refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(1000,true/*,false*/);
            }
        });
        know_refresh.setOnLoadMoreListener(new OnLoadMoreListener(){
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout){
                refreshLayout.finishLoadMore(1000/*,false*/);
            }
        });
    }

    @Override
    public void putKnowData(List<Datum> list) {
        datumList = list;
        mKnowledge_adapter.refreshData(list);
        knowledge_recyclerView.setVisibility(View.VISIBLE);
    }
}
