package com.example.liuyang05_sx.androidstudy.ui.main.model;

import android.util.Log;

import com.example.liuyang05_sx.androidstudy.bean.BaseResult;
import com.example.liuyang05_sx.androidstudy.bean.main.Data;
import com.example.liuyang05_sx.androidstudy.bean.main.Main_Banner;
import com.example.liuyang05_sx.androidstudy.http.HttpHelperImp;
import com.example.liuyang05_sx.androidstudy.ui.main.presenter.BannerPresenter;
import com.example.liuyang05_sx.androidstudy.ui.main.presenter.IBannerCallBack;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.callback.Callback;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.PATCH;

public class Banner_Model implements Banner_IModel {
    @Override
    public void getData(int page,final IBannerCallBack callBack) {
        Observable.zip(HttpHelperImp.httpHelperImp.getBannerData(), HttpHelperImp.httpHelperImp.getMainData(page),
                new BiFunction<BaseResult<List<Main_Banner>>, BaseResult<Data>, List<BaseResult>>() {
                    @Override
                    public List<BaseResult> apply(BaseResult<List<Main_Banner>> listBaseResult, BaseResult<Data> dataBaseResult) throws Exception {
                        List<BaseResult> list =new ArrayList<>();
                        list.add(listBaseResult);
                        list.add(dataBaseResult);
                        return list;
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<BaseResult>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<BaseResult> baseResults) {
                        List<Main_Banner> bannerlist = (List<Main_Banner>) baseResults.get(0).getData();
                        Data data = (Data) baseResults.get(1).getData();
                        callBack.successMain_Data(data.getCurPage(),bannerlist,data.getDatas());
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

    @Override
    public void loadMore(int page, IBannerCallBack callBack) {
        HttpHelperImp.httpHelperImp.getMainData(page).filter(new Predicate<BaseResult<Data>>() {
            @Override
            public boolean test(BaseResult<Data> dataBaseResult) throws Exception {
                return dataBaseResult.getErrorCode()==0;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResult<Data>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResult<Data> dataBaseResult) {
                            callBack.successLoadMore(dataBaseResult.getData().getCurPage(),dataBaseResult.getData().getDatas());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void Save(int id, IBannerCallBack callBack) {
        HttpHelperImp.httpHelperImp.SaveArticle(id)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResult baseResult) {
                        callBack.Savesuccess();
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
