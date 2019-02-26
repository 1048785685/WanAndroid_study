package com.example.liuyang05_sx.androidstudy.utils;

import android.support.annotation.StringRes;
import android.widget.Toast;

import com.example.liuyang05_sx.androidstudy.base.AndroidStudy_Application;


/**
 * Created by Soully on 2017/7/25.
 */

public class ToastUtil{

    public static void show(String content) {
        Toast.makeText(AndroidStudy_Application.getContext(), content, Toast.LENGTH_SHORT).show();
    }

    public static void show(@StringRes int res) {
        Toast.makeText(AndroidStudy_Application.getContext(), res, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(String content) {
        Toast.makeText(AndroidStudy_Application.getContext(), content, Toast.LENGTH_LONG).show();
    }

    public static void showLong(@StringRes int res) {
        Toast.makeText(AndroidStudy_Application.getContext(), res, Toast.LENGTH_SHORT).show();
    }
}

