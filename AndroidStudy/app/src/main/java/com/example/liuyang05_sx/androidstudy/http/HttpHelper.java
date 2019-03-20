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
import com.example.liuyang05_sx.androidstudy.bean.search.SearchDatum;

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
     * 知识体系下的文章
     * http://www.wanandroid.com/article/list/0?cid=60
     *
     * @param page page num
     * @param cid second page id
     * @return 知识体系feed文章数据
     */
    Observable<BaseResult<Data>> getKnowledgeDetailData(int page,int cid);

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
     * 在文章列表页面，取消收藏
     */

    Observable<BaseResult> unCollect(int id);

    /**
     * 取消收藏文章的列表
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

    /**
     * 热门搜索
     */
    Observable<BaseResult<List<SearchDatum>>> HotSearch();

}
