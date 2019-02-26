package com.example.liuyang05_sx.androidstudy.base.presenter;

import com.example.liuyang05_sx.androidstudy.base.view.AbstractView;

public interface AbstractPresenter<V extends AbstractView> {
    void attachView(V view);
    void detachView();
}
