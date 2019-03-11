package com.example.liuyang05_sx.androidstudy.ui.wx_article.presenter;

import com.example.liuyang05_sx.androidstudy.base.presenter.BasePresenter;
import com.example.liuyang05_sx.androidstudy.bean.Wx_and_Pro.Wx_pro_article;
import com.example.liuyang05_sx.androidstudy.ui.wx_article.IWx_Fragment;
import com.example.liuyang05_sx.androidstudy.ui.wx_article.model.Wx_Model;

import java.util.List;

public class Wx_P extends BasePresenter<IWx_Fragment> implements IWx_P {

    private Wx_Model model;
    public Wx_P(){
        model = new Wx_Model();
    }

    @Override
    public void attachView(IWx_Fragment view) {
        super.attachView(view);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    @Override
    public void getTabData() {
        model.getTabData(this);
    }

    @Override
    public void showTabData(List<Wx_pro_article> list) {
        getMvpView().showTabData(list);
    }
}
