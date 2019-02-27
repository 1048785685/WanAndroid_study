package com.example.liuyang05_sx.androidstudy.ui.main.presenter;

import com.example.liuyang05_sx.androidstudy.bean.main.Data;
import com.example.liuyang05_sx.androidstudy.bean.main.Data_;
import com.example.liuyang05_sx.androidstudy.bean.main.Main_Banner;

import java.util.List;

public interface  IBannerCallBack {
    void successLoadMore(int page,List<Data_> data);
    void successMain_Data(int page,List<Main_Banner> data ,List<Data_> list);
}
