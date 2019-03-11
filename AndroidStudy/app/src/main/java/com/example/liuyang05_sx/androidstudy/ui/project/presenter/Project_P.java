package com.example.liuyang05_sx.androidstudy.ui.project.presenter;

import com.example.liuyang05_sx.androidstudy.base.presenter.BasePresenter;
import com.example.liuyang05_sx.androidstudy.bean.Wx_and_Pro.Wx_pro_article;
import com.example.liuyang05_sx.androidstudy.ui.project.IPro_View;
import com.example.liuyang05_sx.androidstudy.ui.project.Model.Project_M;

import java.util.List;

public class Project_P extends BasePresenter<IPro_View> implements IProject_P {

    private Project_M project_m;

    public Project_P(){
        project_m = new Project_M();
    }

    @Override
    public void attachView(IPro_View view) {
        super.attachView(view);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    @Override
    public void getData() {
        project_m.getData(this);
    }

    @Override
    public void showProjectData(List<Wx_pro_article> list) {
        getMvpView().showProjectData(list);
    }
}
