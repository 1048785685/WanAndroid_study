package com.example.liuyang05_sx.androidstudy.ui.knowledge.presenter;

import com.example.liuyang05_sx.androidstudy.base.presenter.BasePresenter;
import com.example.liuyang05_sx.androidstudy.bean.knowledge.Datum;
import com.example.liuyang05_sx.androidstudy.ui.knowledge.IKnow_View;
import com.example.liuyang05_sx.androidstudy.ui.knowledge.model.Know_Model;

import java.util.List;



public class Know_Presenter extends BasePresenter<IKnow_View>  implements IKnow_Presenter{
    private Know_Model know_model ;

    public Know_Presenter(){
        know_model = new Know_Model();
    }

    @Override
    public void attachView(IKnow_View view) {
        super.attachView(view);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    @Override
    public void getKnowData() {
        know_model.getKnowData(this);
    }

    @Override
    public void fetchKnowDataSuccess(List<Datum> list) {
        getMvpView().putKnowData(list);
    }
}
