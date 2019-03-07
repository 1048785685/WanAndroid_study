package com.example.liuyang05_sx.androidstudy.http;

import com.example.liuyang05_sx.androidstudy.bean.BaseResult;
import com.example.liuyang05_sx.androidstudy.bean.knowledge.Datum;
import com.example.liuyang05_sx.androidstudy.bean.main.Data;
import com.example.liuyang05_sx.androidstudy.bean.main.LoginData;
import com.example.liuyang05_sx.androidstudy.bean.main.Main_Banner;
import com.example.liuyang05_sx.androidstudy.bean.navigation.NavData;

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
    /*
    知识体系数据
    */
    Observable<BaseResult<List<Datum>>> getTreeData();
    /*
    导航
    */
    Observable<BaseResult<List<NavData>>> getNaviData();
    /*
    登录
    */
    Observable<BaseResult<LoginData>> Login_in(String username, String password);
    /*
    退出
     */
    Observable<BaseResult> Login_out();
    /*
    注册
     */
    Observable<BaseResult> Register(String username,String password,String repassword);
}
