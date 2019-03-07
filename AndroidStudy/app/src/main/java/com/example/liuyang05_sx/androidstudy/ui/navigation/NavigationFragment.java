package com.example.liuyang05_sx.androidstudy.ui.navigation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liuyang05_sx.androidstudy.R;
import com.example.liuyang05_sx.androidstudy.base.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import q.rorbin.verticaltablayout.VerticalTabLayout;

public class NavigationFragment extends BaseFragment {
    private View view;
    @BindView(R.id.navigation_recycler)
    RecyclerView navigation_recycler;
    @BindView(R.id.navigation_TabLayout)
    VerticalTabLayout navigation_TabLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_navigation,container,false);
        ButterKnife.bind(this,view);


        return view;
    }
}
