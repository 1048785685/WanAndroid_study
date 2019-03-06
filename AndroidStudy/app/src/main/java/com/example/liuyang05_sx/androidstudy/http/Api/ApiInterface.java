package com.example.liuyang05_sx.androidstudy.http.Api;

import com.example.liuyang05_sx.androidstudy.bean.BaseResult;
import com.example.liuyang05_sx.androidstudy.bean.knowledge.Datum;
import com.example.liuyang05_sx.androidstudy.bean.main.Data;
import com.example.liuyang05_sx.androidstudy.bean.main.LoginData;
import com.example.liuyang05_sx.androidstudy.bean.main.Main_Banner;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
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
    /*
    知识体系数据
    */
    @GET("tree/json")
    Observable<BaseResult<List<Datum>>> getTreeData();
    /*
    登录
     */
    @POST("user/login")
    @FormUrlEncoded
    Observable<BaseResult<LoginData>> Login_in(@Field("username") String username
            ,@Field("password") String password);
    /*
    退出
     */
    @GET("user/logout/json")
    Observable<BaseResult> Login_out();
    /*
    注册
     */
    @POST("user/register")
    @FormUrlEncoded
    Observable<BaseResult> Register(@Field("username") String username
            ,@Field("password") String password,@Field("repassword") String repassword);


}
