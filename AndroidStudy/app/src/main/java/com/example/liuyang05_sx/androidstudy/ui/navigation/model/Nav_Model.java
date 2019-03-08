package com.example.liuyang05_sx.androidstudy.ui.navigation.model;

import android.util.Log;

import com.example.liuyang05_sx.androidstudy.bean.BaseResult;
import com.example.liuyang05_sx.androidstudy.bean.navigation.NavData;
import com.example.liuyang05_sx.androidstudy.http.HttpHelperImp;
import com.example.liuyang05_sx.androidstudy.ui.navigation.presenter.INav_P;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Nav_Model implements INav_M{
    @Override
    public void getData(INav_P callback) {
        HttpHelperImp.httpHelperImp.getNaviData()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResult<List<NavData>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResult<List<NavData>> listBaseResult) {
                        if (listBaseResult.getErrorCode()==0){
                            callback.putNavData(listBaseResult.getData());
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
