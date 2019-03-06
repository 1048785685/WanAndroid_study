package com.example.liuyang05_sx.androidstudy.ui.knowledge;

import com.example.liuyang05_sx.androidstudy.base.view.AbstractView;
import com.example.liuyang05_sx.androidstudy.bean.knowledge.Datum;

import java.util.List;

public interface IKnow_View extends AbstractView {
    void putKnowData(List<Datum> list);
}
