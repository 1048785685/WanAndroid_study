package com.example.liuyang05_sx.androidstudy.ui.navigation.presenter;

import com.example.liuyang05_sx.androidstudy.bean.navigation.NavData;

import java.util.List;

public interface INav_P {
    void getNavData();
    void putNavData(List<NavData> list);
}
