package com.example.liuyang05_sx.androidstudy.ui.knowledge.presenter;

import com.example.liuyang05_sx.androidstudy.bean.knowledge.Datum;

import java.util.List;

public interface IKnow_Presenter {
    void getKnowData();
    void fetchKnowDataSuccess(List<Datum> list);
}
