package com.example.liuyang05_sx.androidstudy.ui.main.presenter;

import android.content.Context;
import android.util.Log;

import com.example.liuyang05_sx.androidstudy.base.presenter.BasePresenter;
import com.example.liuyang05_sx.androidstudy.base.view.AbstractView;
import com.example.liuyang05_sx.androidstudy.bean.main.Data;
import com.example.liuyang05_sx.androidstudy.bean.main.Data_;
import com.example.liuyang05_sx.androidstudy.bean.main.Main_Banner;
import com.example.liuyang05_sx.androidstudy.ui.main.fragment.IBannerView;
import com.example.liuyang05_sx.androidstudy.ui.main.model.Banner_Model;

import java.util.Collections;
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

    public void getData(int page){
//        getMvpView().startLoading();
        mBanner_Model.getData(page,this);
    }
    @Override
    public void successLoadMore(int page,List<Data_> data){
        getMvpView().showLoadMore(page,data);
    }

    @Override
    public void successMain_Data(int page,List<Main_Banner> data, List<Data_> list) {

        getMvpView().showDataView(page,data,list);
    }

    public void loadMore(int page) {
        mBanner_Model.loadMore(page,this);
    }
}
