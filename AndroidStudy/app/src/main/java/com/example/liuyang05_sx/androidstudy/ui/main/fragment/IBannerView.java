package com.example.liuyang05_sx.androidstudy.ui.main.fragment;

import com.example.liuyang05_sx.androidstudy.base.view.AbstractView;
import com.example.liuyang05_sx.androidstudy.bean.main.Main_Banner;

import java.util.List;

public interface IBannerView extends AbstractView {
    void showDataView(List<Main_Banner> list);
    void refresh(List<Main_Banner> list);
}
