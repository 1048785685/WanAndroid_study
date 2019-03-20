package com.example.liuyang05_sx.androidstudy.ui.main.fragment;

import com.example.liuyang05_sx.androidstudy.base.view.AbstractView;
import com.example.liuyang05_sx.androidstudy.bean.main.Data;
import com.example.liuyang05_sx.androidstudy.bean.main.Data_;
import com.example.liuyang05_sx.androidstudy.bean.main.Main_Banner;

import java.util.List;

public interface IBannerView extends AbstractView {

    void showDataView(int page, List<Main_Banner> list,List<Data_> Main_list);
    void showLoadMore(int page,List<Data_> list);

    void savesuccess();

    void Cancel();
}
