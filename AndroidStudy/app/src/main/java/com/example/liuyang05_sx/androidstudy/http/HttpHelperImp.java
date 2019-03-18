package com.example.liuyang05_sx.androidstudy.http;

import com.example.liuyang05_sx.androidstudy.bean.BaseResult;
import com.example.liuyang05_sx.androidstudy.bean.Wx_and_Pro.Wx_pro_article;
import com.example.liuyang05_sx.androidstudy.bean.knowledge.Datum;
import com.example.liuyang05_sx.androidstudy.bean.main.Data;
import com.example.liuyang05_sx.androidstudy.bean.main.LoginData;
import com.example.liuyang05_sx.androidstudy.bean.main.Main_Banner;
import com.example.liuyang05_sx.androidstudy.bean.navigation.NavData;
import com.example.liuyang05_sx.androidstudy.bean.project.pro_Data;
import com.example.liuyang05_sx.androidstudy.bean.save.ShowSaveData;

import java.util.List;

import io.reactivex.Observable;

public class HttpHelperImp implements HttpHelper{
    public static HttpHelperImp httpHelperImp = new HttpHelperImp();


    @Override
    public Observable<BaseResult<List<Main_Banner>>> getBannerData() {
        return RetrofitSingleton.getApiService().getBannerData();
    }

    @Override
    public Observable<BaseResult<Data>> getMainData(int page) {
        return RetrofitSingleton.getApiService().getMainData(page);
    }

    @Override
    public Observable<BaseResult<List<Datum>>> getTreeData() {
        return RetrofitSingleton.getApiService().getTreeData();
    }

    @Override
    public Observable<BaseResult<Data>> getKnowledgeDetailData(int page, int cid) {
        return RetrofitSingleton.getApiService().getKnowledgeDetailData(page,cid);
    }

    @Override
    public Observable<BaseResult<List<Wx_pro_article>>> getWxData() {
        return RetrofitSingleton.getApiService().getWxData();
    }

    @Override
    public Observable<BaseResult<Data>> getWxDetailData(int id, int page) {
        return RetrofitSingleton.getApiService().getWxDetailData(id,page);
    }

    @Override
    public Observable<BaseResult<List<NavData>>> getNaviData() {
        return RetrofitSingleton.getApiService().getNaviData();
    }

    @Override
    public Observable<BaseResult<List<Wx_pro_article>>> getProjectData() {
        return RetrofitSingleton.getApiService().getProjectData();
    }

    @Override
    public Observable<BaseResult<pro_Data>> getProjectDetailData(int page, int cid) {
        return RetrofitSingleton.getApiService().getProjectDetailData(page,cid);
    }

    @Override
    public Observable<BaseResult> SaveArticle(int id) {
        return RetrofitSingleton.getApiService().SaveArticle(id);
    }

    @Override
    public Observable<BaseResult<ShowSaveData>> CollectArticle(int page) {
        return RetrofitSingleton.getApiService().CollectArticle(page);
    }

    @Override
    public Observable<BaseResult> unCollectArticle(int id,int originId) {
        return RetrofitSingleton.getApiService().unCollectArticle(id,originId);
    }

    @Override
    public Observable<BaseResult<LoginData>> Login_in(String username, String password) {
        return RetrofitSingleton.getApiService().Login_in(username,password);
    }

    @Override
    public Observable<BaseResult> Login_out() {
        return RetrofitSingleton.getApiService().Login_out();
    }

    @Override
    public Observable<BaseResult> Register(String username, String password, String repassword) {
        return RetrofitSingleton.getApiService().Register(username,password,repassword);
    }
}
