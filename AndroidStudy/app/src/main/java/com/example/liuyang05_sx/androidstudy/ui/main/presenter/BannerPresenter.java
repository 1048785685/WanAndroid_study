package com.example.liuyang05_sx.androidstudy.ui.main.presenter;

import android.content.Context;
import android.util.Log;

import com.example.liuyang05_sx.androidstudy.base.presenter.BasePresenter;
import com.example.liuyang05_sx.androidstudy.base.view.AbstractView;
import com.example.liuyang05_sx.androidstudy.bean.main.Main_Banner;
import com.example.liuyang05_sx.androidstudy.ui.main.fragment.IBannerView;
import com.example.liuyang05_sx.androidstudy.ui.main.model.Banner_Model;

import java.util.List;

public class BannerPresenter extends BasePresenter<IBannerView> implements IBannerCallBack{
    private Banner_Model mBanner_Model;

    public BannerPresenter(){
        mBanner_Model = new Banner_Model();
    }

    @Override
    public void attachView(IBannerView view) {
        super.attachView(view);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void getData(){
        getMvpView().startLoading();
        mBanner_Model.getData(this);
    }

    @Override
    public void success(List<Main_Banner> data){
        getMvpView().showDataView(data);
    }
}
