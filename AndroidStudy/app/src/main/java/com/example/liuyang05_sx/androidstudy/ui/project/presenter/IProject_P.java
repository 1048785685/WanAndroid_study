package com.example.liuyang05_sx.androidstudy.ui.project.presenter;

import com.example.liuyang05_sx.androidstudy.bean.Wx_and_Pro.Wx_pro_article;

import java.util.List;

public interface IProject_P {
    void getData();
    void showProjectData(List<Wx_pro_article> list);
}
