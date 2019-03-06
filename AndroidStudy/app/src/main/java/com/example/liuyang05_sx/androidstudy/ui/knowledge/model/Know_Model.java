package com.example.liuyang05_sx.androidstudy.ui.knowledge.model;

import android.widget.Toast;

import com.example.liuyang05_sx.androidstudy.bean.BaseResult;
import com.example.liuyang05_sx.androidstudy.bean.knowledge.Datum;
import com.example.liuyang05_sx.androidstudy.http.HttpHelperImp;
import com.example.liuyang05_sx.androidstudy.ui.knowledge.presenter.IKnow_Presenter;

import java.util.List;

import javax.security.auth.callback.Callback;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Know_Model implements IKnow_Model {
    public void getKnowData(IKnow_Presenter callback) {
        HttpHelperImp.httpHelperImp.getTreeData()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResult<List<Datum>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResult<List<Datum>> listBaseResult) {
                        if (listBaseResult.getErrorCode()==0){
                            callback.fetchKnowDataSuccess(listBaseResult.getData());
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
