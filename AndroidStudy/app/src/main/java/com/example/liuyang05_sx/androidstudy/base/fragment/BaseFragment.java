package com.example.liuyang05_sx.androidstudy.base.fragment;

import com.example.liuyang05_sx.androidstudy.base.presenter.AbstractPresenter;
import com.example.liuyang05_sx.androidstudy.base.view.AbstractView;


public class BaseFragment<T extends AbstractPresenter> implements AbstractView {
    @Override
    public void startLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
