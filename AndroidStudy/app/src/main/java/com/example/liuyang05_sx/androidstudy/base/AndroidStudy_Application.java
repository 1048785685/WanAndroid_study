package com.example.liuyang05_sx.androidstudy.base;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.example.liuyang05_sx.androidstudy.http.RetrofitSingleton;


public class AndroidStudy_Application extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }
    public static Context getContext(){
        return  mContext;
    }
}
