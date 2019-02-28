package com.example.liuyang05_sx.androidstudy.http;

import android.content.Context;
import android.util.Log;

import com.example.liuyang05_sx.androidstudy.R;
import com.example.liuyang05_sx.androidstudy.http.Api.ApiInterface;
import com.example.liuyang05_sx.androidstudy.utils.ToastUtil;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSingleton {
    private static ApiInterface apiService = null;
    private static Retrofit retrofit = null;
    private static OkHttpClient okHttpClient = null;


    private RetrofitSingleton() {
        initOkHttp();
        initRetrofit();
        apiService = retrofit.create(ApiInterface.class);
    }

    public static RetrofitSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static  RetrofitSingleton INSTANCE = new RetrofitSingleton();
    }

    public static ApiInterface getApiService() {
        if (apiService == null) {
            throw new NullPointerException("get apiService must be called after init");
        }
        return apiService;
    }

    private static void initOkHttp() {
        // https://drakeet.me/retrofit-2-0-okhttp-3-0-config
        //设置retrofit拦截器，打印出请求体
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        CookieJar mCookieJar = new CookieJar() {
            private final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();

            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                cookieStore.put(url.host(), cookies);
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {

                List<Cookie> cookies = cookieStore.get(url.host());
                return cookies != null ? cookies : new ArrayList<Cookie>();
            }
        };

        okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .addInterceptor(interceptor)
                .cookieJar(mCookieJar)
                .retryOnConnectionFailure(true)//断网自动重连
                .connectTimeout(15, TimeUnit.SECONDS)
                .build();
    }

    private static void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterface.HOST)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static void disposeFailureInfo(Throwable t, Context context) {
        if (context != null) {
            if (t.toString().contains("GaiException")
                    || t.toString().contains("SocketTimeoutException")
                    || t.toString().contains("UnknownHostException")) {
                ToastUtil.showLong("网络不给力");
            } else if (t.toString().contains("ConnectException")) {
                ToastUtil.showLong("连接服务器失败，请重试");
            }  else {
                ToastUtil.show(t.getMessage());

            }
        }
    }

}
