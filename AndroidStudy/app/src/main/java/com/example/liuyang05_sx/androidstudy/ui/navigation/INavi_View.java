package com.example.liuyang05_sx.androidstudy.ui.navigation;

import com.example.liuyang05_sx.androidstudy.base.view.AbstractView;
import com.example.liuyang05_sx.androidstudy.bean.navigation.NavData;

import java.util.List;

public interface INavi_View extends AbstractView {
    void putNavData(List<NavData> list);
}
