package com.example.liuyang05_sx.androidstudy.base.fragment;

import android.support.v4.app.Fragment;

import com.example.liuyang05_sx.androidstudy.base.presenter.AbstractPresenter;
import com.example.liuyang05_sx.androidstudy.base.view.AbstractView;


public class BaseFragment<T extends AbstractPresenter> extends Fragment implements AbstractView {
    @Override
    public void startLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
