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


public interface HttpHelper {
    /**
    首页Banner数据
     */
    Observable<BaseResult<List<Main_Banner>>> getBannerData();

    /**
    首页文章数据
     */
    Observable<BaseResult<Data>> getMainData(int page);

    /**
    知识体系数据
    */
    Observable<BaseResult<List<Datum>>> getTreeData();

    /**
    微信公众号tab
     */
    Observable<BaseResult<List<Wx_pro_article>>> getWxData();

    /**
    微信公众号下的文章目录
     */
    Observable<BaseResult<Data>> getWxDetailData( int id, int page);

    /**
    导航
    */
    Observable<BaseResult<List<NavData>>> getNaviData();

    /**
     获取项目列表
     */
    Observable<BaseResult<List<Wx_pro_article>>> getProjectData();

    /**
     * 获取项目的文章目录
     */
    Observable<BaseResult<pro_Data>> getProjectDetailData(int page,int cid);

    /**
     * 收藏站内文章
     */
    Observable<BaseResult> SaveArticle(int id);

    /**
     * 收藏文章的列表
     */
    Observable<BaseResult<ShowSaveData>> CollectArticle(int page);

    /**
     * 收藏文章的列表
     */
    Observable<BaseResult> unCollectArticle(int id,int originId);

    /**
    登录
    */
    Observable<BaseResult<LoginData>> Login_in(String username, String password);

    /**
    退出
     */
    Observable<BaseResult> Login_out();

    /**
    注册
     */
    Observable<BaseResult> Register(String username,String password,String repassword);

}
