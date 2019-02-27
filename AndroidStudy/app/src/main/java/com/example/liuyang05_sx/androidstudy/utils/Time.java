package com.example.liuyang05_sx.androidstudy.utils;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Time {
    @SuppressLint("SimpleDateFormat")
    public static String getTime(String format, long date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }
}
