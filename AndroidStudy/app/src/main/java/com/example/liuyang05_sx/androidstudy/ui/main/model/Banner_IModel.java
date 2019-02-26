package com.example.liuyang05_sx.androidstudy.ui.main.model;

import com.example.liuyang05_sx.androidstudy.ui.main.presenter.IBannerCallBack;

import javax.security.auth.callback.Callback;

public interface Banner_IModel {
    void getData(IBannerCallBack callBack);
}
