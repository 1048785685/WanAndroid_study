package com.example.liuyang05_sx.androidstudy.ui.wx_article.presenter;

import com.example.liuyang05_sx.androidstudy.bean.Wx_and_Pro.Wx_pro_article;

import java.util.List;

public interface IWx_P {
    void getTabData();
    void showTabData(List<Wx_pro_article> list);
}
