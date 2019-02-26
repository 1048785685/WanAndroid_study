package com.example.liuyang05_sx.androidstudy.http;

import com.example.liuyang05_sx.androidstudy.bean.BaseResult;
import com.example.liuyang05_sx.androidstudy.bean.main.Main_Banner;

import java.util.List;

import io.reactivex.Observable;

public class HttpHelperImp implements HttpHelper{
    public static HttpHelperImp httpHelperImp = new HttpHelperImp();
    private static RetrofitSingleton retrofitSingleton = RetrofitSingleton.getInstance();
    @Override
    public Observable<BaseResult<List<Main_Banner>>> getBannerData() {
        return retrofitSingleton.getApiService().getBannerData();
    }
}
