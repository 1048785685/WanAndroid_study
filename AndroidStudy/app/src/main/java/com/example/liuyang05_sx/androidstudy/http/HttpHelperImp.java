package com.example.liuyang05_sx.androidstudy.http;

import com.example.liuyang05_sx.androidstudy.bean.BaseResult;
import com.example.liuyang05_sx.androidstudy.bean.main.Data;
import com.example.liuyang05_sx.androidstudy.bean.main.LoginData;
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

    @Override
    public Observable<BaseResult<Data>> getMainData(int page) {
        return retrofitSingleton.getApiService().getMainData(page);
    }

    @Override
    public Observable<BaseResult<LoginData>> Login_in(String username, String password) {
        return retrofitSingleton.getApiService().Login_in(username,password);
    }

    @Override
    public Observable<BaseResult> Register(String username, String password, String repassword) {
        return retrofitSingleton.getApiService().Register(username,password,repassword);
    }
}
