package com.example.liuyang05_sx.androidstudy.http.Api;

import com.example.liuyang05_sx.androidstudy.bean.BaseResult;
import com.example.liuyang05_sx.androidstudy.bean.main.Main_Banner;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiInterface {
    String HOST = "http://www.wanandroid.com/";
    /*
    首页Banner数据
     */
    @GET("banner/json")
    Observable<BaseResult<List<Main_Banner>>> getBannerData();
}
