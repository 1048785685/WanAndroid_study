package com.example.liuyang05_sx.androidstudy.base.presenter;

import com.example.liuyang05_sx.androidstudy.base.view.AbstractView;


public class BasePresenter<T extends AbstractView> implements AbstractPresenter<T>{
    private T mMvpView;
    @Override
    public void attachView(T view) {
        mMvpView = view;
    }

    @Override
    public void detachView() {
        mMvpView=null;
    }
    public T getmMvpView(){
        return mMvpView;
    }
}
