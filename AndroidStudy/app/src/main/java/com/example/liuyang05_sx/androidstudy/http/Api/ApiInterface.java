package com.example.liuyang05_sx.androidstudy.http.Api;

import com.example.liuyang05_sx.androidstudy.bean.BaseResult;
import com.example.liuyang05_sx.androidstudy.bean.Wx_and_Pro.Wx_pro_article;
import com.example.liuyang05_sx.androidstudy.bean.knowledge.Datum;
import com.example.liuyang05_sx.androidstudy.bean.main.Data;
import com.example.liuyang05_sx.androidstudy.bean.main.LoginData;
import com.example.liuyang05_sx.androidstudy.bean.main.Main_Banner;
import com.example.liuyang05_sx.androidstudy.bean.navigation.NavData;
import com.example.liuyang05_sx.androidstudy.bean.project.pro_Data;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    String HOST = "http://www.wanandroid.com/";

    /**
    首页Banner数据
     */
    @GET("banner/json")
    Observable<BaseResult<List<Main_Banner>>> getBannerData();

    /**
    首页文章数据
     */
    @GET("article/list/{page}/json")
    Observable<BaseResult<Data>> getMainData(@Path("page") int page);

    /**
    知识体系数据
    */
    @GET("tree/json")
    Observable<BaseResult<List<Datum>>> getTreeData();

    /**
    微信公众号tab
     */
    @GET("wxarticle/chapters/json")
    Observable<BaseResult<List<Wx_pro_article>>> getWxData();

    /**
    微信公众号下的文章目录
     */
    @GET("wxarticle/list/{id}/{page}/json")
    Observable<BaseResult<Data>> getWxDetailData(@Path("id") int id,@Path("page") int page);

    /**
    导航
    */
    @GET("navi/json")
    Observable<BaseResult<List<NavData>>> getNaviData();

    /**
     获取项目列表
     */
    @GET("project/tree/json")
    Observable<BaseResult<List<Wx_pro_article>>> getProjectData();

    /**
     * 获取项目的文章目录
     */
    @GET("project/list/{page}/json")
    Observable<BaseResult<pro_Data>> getProjectDetailData(@Path("page") int page, @Query("cid") int cid);

    /**
    登录
     */
    @POST("user/login")
    @FormUrlEncoded
    Observable<BaseResult<LoginData>> Login_in(@Field("username") String username
            ,@Field("password") String password);

    /**
    退出
     */
    @GET("user/logout/json")
    Observable<BaseResult> Login_out();

    /**
    注册
     */
    @POST("user/register")
    @FormUrlEncoded
    Observable<BaseResult> Register(@Field("username") String username
            ,@Field("password") String password,@Field("repassword") String repassword);


}
