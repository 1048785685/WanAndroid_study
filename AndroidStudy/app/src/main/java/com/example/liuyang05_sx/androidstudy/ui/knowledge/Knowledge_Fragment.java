package com.example.liuyang05_sx.androidstudy.ui.knowledge;

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
import com.example.liuyang05_sx.androidstudy.ui.knowledge.adapter.Knowledge_RecyclerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Knowledge_Fragment extends BaseFragment {
    @BindView(R.id.knowledge_recycler)
    RecyclerView knowledge_recyclerView;
    private View view;
    private Knowledge_RecyclerAdapter mKnowledge_adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_knowledge,container,false);
        ButterKnife.bind(this,view);
        initRecyclerView();
        return view;
    }

    private void initRecyclerView() {
        knowledge_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL,false));
        mKnowledge_adapter = new Knowledge_RecyclerAdapter(view.getContext());
        knowledge_recyclerView.setAdapter(mKnowledge_adapter);
        knowledge_recyclerView.setVisibility(View.VISIBLE);
        mKnowledge_adapter.setOnRecyclerListener(new Knowledge_RecyclerAdapter.OnRecyclerListener() {
            @Override
            public void onItemListener(int position) {
                Toast.makeText(view.getContext(),"点击第"+position+"按钮",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
