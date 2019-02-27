package com.example.liuyang05_sx.androidstudy.http;

import com.example.liuyang05_sx.androidstudy.bean.BaseResult;
import com.example.liuyang05_sx.androidstudy.bean.main.Data;
import com.example.liuyang05_sx.androidstudy.bean.main.Main_Banner;

import java.util.List;

import io.reactivex.Observable;


public interface HttpHelper {
    /*
    首页Banner数据
     */
    Observable<BaseResult<List<Main_Banner>>> getBannerData();
    /*
    首页文章数据
     */
    Observable<BaseResult<Data>> getMainData(int page);
}
