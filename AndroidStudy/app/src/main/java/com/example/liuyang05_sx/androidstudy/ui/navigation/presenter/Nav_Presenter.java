package com.example.liuyang05_sx.androidstudy.ui.navigation.presenter;

import android.util.Log;

import com.example.liuyang05_sx.androidstudy.base.presenter.BasePresenter;
import com.example.liuyang05_sx.androidstudy.bean.navigation.NavData;
import com.example.liuyang05_sx.androidstudy.ui.navigation.INavi_View;
import com.example.liuyang05_sx.androidstudy.ui.navigation.model.Nav_Model;

import java.util.List;

public class Nav_Presenter extends BasePresenter<INavi_View> implements INav_P{

    private Nav_Model nav_model;

    public Nav_Presenter(){
        nav_model = new Nav_Model();
    }
    @Override
    public void attachView(INavi_View view) {
        super.attachView(view);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    @Override
    public void getNavData() {
        nav_model.getData(this);
    }

    @Override
    public void putNavData(List<NavData> list) {

        getMvpView().putNavData(list);
    }
}
