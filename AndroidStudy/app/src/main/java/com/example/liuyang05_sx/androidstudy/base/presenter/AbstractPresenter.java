package com.example.liuyang05_sx.androidstudy.base.presenter;

import com.example.liuyang05_sx.androidstudy.base.view.AbstractView;

public interface AbstractPresenter<T extends AbstractView> {
    void attachView(T view);
    void detachView();
}
