package com.example.liuyang05_sx.androidstudy.ui.save;

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

public class Save_fragment extends BaseFragment {
    private View view;
    @BindView(R.id.save_recycler)
    RecyclerView save_recycler;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_save,container,false);
        ButterKnife.bind(this,view);
        return view;
    }
}
