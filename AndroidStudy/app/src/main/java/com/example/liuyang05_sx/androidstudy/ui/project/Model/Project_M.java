package com.example.liuyang05_sx.androidstudy.ui.project.Model;

import com.example.liuyang05_sx.androidstudy.bean.BaseResult;
import com.example.liuyang05_sx.androidstudy.bean.Wx_and_Pro.Wx_pro_article;
import com.example.liuyang05_sx.androidstudy.bean.main.Data;
import com.example.liuyang05_sx.androidstudy.http.HttpHelper;
import com.example.liuyang05_sx.androidstudy.http.HttpHelperImp;
import com.example.liuyang05_sx.androidstudy.ui.project.presenter.IProject_P;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Project_M implements IPro_M {
    @Override
    public void getData(IProject_P callback) {
        HttpHelperImp.httpHelperImp.getProjectData()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResult<List<Wx_pro_article>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResult<List<Wx_pro_article>> listBaseResult) {
                        if (listBaseResult.getErrorCode() == 0){
                            callback.showProjectData(listBaseResult.getData());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
