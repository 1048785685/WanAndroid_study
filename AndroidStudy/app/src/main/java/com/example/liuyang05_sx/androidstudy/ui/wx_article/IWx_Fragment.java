package com.example.liuyang05_sx.androidstudy.ui.wx_article;

import com.example.liuyang05_sx.androidstudy.base.view.AbstractView;
import com.example.liuyang05_sx.androidstudy.bean.Wx_and_Pro.Wx_pro_article;

import java.util.List;

public interface IWx_Fragment extends AbstractView {
    void showTabData(List<Wx_pro_article> list);
}
