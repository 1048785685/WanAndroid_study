package com.example.liuyang05_sx.androidstudy.ui.main.model;

import com.example.liuyang05_sx.androidstudy.ui.main.presenter.BannerPresenter;
import com.example.liuyang05_sx.androidstudy.ui.main.presenter.IBannerCallBack;

import javax.security.auth.callback.Callback;

public interface Banner_IModel {
    void getData(int page,IBannerCallBack callBack);
    void loadMore(int page,IBannerCallBack callBack);

    void Save(int id, IBannerCallBack bannerPresenter);

    void unCollect(int id, IBannerCallBack bannerPresenter);
}
