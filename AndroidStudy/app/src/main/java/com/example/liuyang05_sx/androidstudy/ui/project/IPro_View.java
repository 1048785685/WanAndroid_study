package com.example.liuyang05_sx.androidstudy.ui.project;

import com.example.liuyang05_sx.androidstudy.base.view.AbstractView;
import com.example.liuyang05_sx.androidstudy.bean.Wx_and_Pro.Wx_pro_article;

import java.util.List;

public interface IPro_View extends AbstractView {
    void showProjectData(List<Wx_pro_article> list);
}
