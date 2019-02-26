package com.example.liuyang05_sx.androidstudy.ui.main.model;

import android.util.Log;

import com.example.liuyang05_sx.androidstudy.bean.BaseResult;
import com.example.liuyang05_sx.androidstudy.bean.main.Main_Banner;
import com.example.liuyang05_sx.androidstudy.http.HttpHelperImp;
import com.example.liuyang05_sx.androidstudy.ui.main.presenter.IBannerCallBack;

import java.util.List;

import javax.security.auth.callback.Callback;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class Banner_Model implements Banner_IModel {
    @Override
    public void getData(final IBannerCallBack callBack) {
        HttpHelperImp.httpHelperImp.getBannerData().filter(new Predicate<BaseResult<List<Main_Banner>>>() {
            @Override
            public boolean test(BaseResult<List<Main_Banner>> listBaseResult) throws Exception {
                return listBaseResult.getErrorCode()==0;
            }
        }).subscribeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResult<List<Main_Banner>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResult<List<Main_Banner>> listBaseResult) {
                        callBack.success(listBaseResult.getData());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
