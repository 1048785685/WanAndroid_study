package com.example.liuyang05_sx.androidstudy.http.Api;

import com.example.liuyang05_sx.androidstudy.bean.BaseResult;
import com.example.liuyang05_sx.androidstudy.bean.main.Data;
import com.example.liuyang05_sx.androidstudy.bean.main.Main_Banner;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    String HOST = "http://www.wanandroid.com/";
    /*
    首页Banner数据
     */
    @GET("banner/json")
    Observable<BaseResult<List<Main_Banner>>> getBannerData();
    /*
    首页文章数据
     */
    @GET("article/list/{page}/json")
    Observable<BaseResult<Data>> getMainData(@Path("page") int page);
}
