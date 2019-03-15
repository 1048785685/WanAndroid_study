package com.example.liuyang05_sx.androidstudy.ui.save;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.liuyang05_sx.androidstudy.R;
import com.example.liuyang05_sx.androidstudy.base.BaseActivity;
import com.example.liuyang05_sx.androidstudy.bean.BaseResult;
import com.example.liuyang05_sx.androidstudy.bean.event.CollectEvent;
import com.example.liuyang05_sx.androidstudy.bean.event.LoginEvent;
import com.example.liuyang05_sx.androidstudy.bean.save.ShowSaveData;
import com.example.liuyang05_sx.androidstudy.bean.save.ShowSaveData_;
import com.example.liuyang05_sx.androidstudy.http.HttpHelperImp;
import com.example.liuyang05_sx.androidstudy.ui.main.activity.MainActivity;
import com.example.liuyang05_sx.androidstudy.utils.DividerItemDecoration;
import com.example.liuyang05_sx.androidstudy.utils.RxBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CollectActivity extends BaseActivity {
    private SaveAdapter saveAdapter;
    private List<ShowSaveData_> list = new ArrayList<>();
    @BindView(R.id.save_recycler)
    RecyclerView save_recycler;
    @BindView(R.id.save_toolbar)
    Toolbar save_toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        ButterKnife.bind(this);
        init();
        registerLoginEvent();
        getCollect();
    }
    private void init() {



        setSupportActionBar(save_toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        save_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        save_recycler.addItemDecoration(new DividerItemDecoration(CollectActivity.this,DividerItemDecoration.VERTICAL_LIST));
        save_recycler.setLayoutManager(new LinearLayoutManager(CollectActivity.this,
                LinearLayoutManager.VERTICAL,false));
        save_recycler.setVisibility(View.VISIBLE);
        saveAdapter = new SaveAdapter(CollectActivity.this,list);
        save_recycler.setAdapter(saveAdapter);
        save_recycler.setVisibility(View.INVISIBLE);
        saveAdapter.OnSaveRecyclerClick(new SaveAdapter.SaveOnRecyclerView() {
            @Override
            public void onLikeClick(int position) {
                unCollect(position);
            }

            @Override
            public void onItemClick() {

            }
        });
    }

    private void unCollect(int position) {
        HttpHelperImp.httpHelperImp.unCollectArticle(list.get(position).getId(),list.get(position).getOriginId())
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResult baseResult) {
//                        getCollect();
                        RxBus.getDefault().post(new CollectEvent());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void getCollect() {
        HttpHelperImp.httpHelperImp.CollectArticle(0)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResult<ShowSaveData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResult<ShowSaveData> showSaveDataBaseResult) {
                        list = showSaveDataBaseResult.getData().getDatas();
                        saveAdapter.dataChanged(list);
                        save_recycler.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @SuppressLint("CheckResult")
    private void registerLoginEvent() {

        RxBus.getDefault().toObservable(this,LoginEvent.class).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LoginEvent>() {
                    @Override
                    public void accept(LoginEvent loginEvent) throws Exception{
                        if (loginEvent.isLogin()) {

                        }else if (!loginEvent.isLogin()){
                            Intent intent = new Intent();
                            intent.setClass(CollectActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
        RxBus.getDefault().toObservable(this,CollectEvent.class).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CollectEvent>() {
                    @Override
                    public void accept(CollectEvent collectEvent) throws Exception {
                        getCollect();
                    }
                });
    }
}
