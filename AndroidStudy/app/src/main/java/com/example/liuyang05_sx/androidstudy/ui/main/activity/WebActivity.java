package com.example.liuyang05_sx.androidstudy.ui.main.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.liuyang05_sx.androidstudy.R;
import com.example.liuyang05_sx.androidstudy.base.BaseActivity;
import com.example.liuyang05_sx.androidstudy.bean.BaseResult;
import com.example.liuyang05_sx.androidstudy.bean.event.CollectEvent;
import com.example.liuyang05_sx.androidstudy.http.HttpHelperImp;
import com.example.liuyang05_sx.androidstudy.utils.C;
import com.example.liuyang05_sx.androidstudy.utils.RxBus;
import com.example.liuyang05_sx.androidstudy.utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static android.view.KeyEvent.KEYCODE_BACK;

public class WebActivity extends BaseActivity {
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.webView_toolbar)
    Toolbar webView_toolbar;
    @BindView(R.id.web_image)
    ImageView web_image;

    private boolean collect;
    @Override
    protected void onDestroy() {
        if (webView != null) {
            webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            webView.clearHistory();

            ((ViewGroup) webView.getParent()).removeView(webView);
            webView.destroy();
            webView = null;
        }
        super.onDestroy();
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        String title = intent.getStringExtra("title");
        collect = intent.getBooleanExtra("like",false);
        int id = intent.getIntExtra("id",-1);
        String display = intent.getStringExtra("display");
        int originId = intent.getIntExtra("originId",-1);
        if (display!=null){
            web_image.setVisibility(View.INVISIBLE);
        }else if (collect){
            web_image.setImageResource(R.drawable.icon_like_selected);
        }else{
            web_image.setImageResource(R.drawable.icon_like_article_not_selected);
        }
        setSupportActionBar(webView_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(Html.fromHtml(title));
        webView_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
        WebSettings mSettings = webView.getSettings();
        mSettings.setJavaScriptEnabled(true);
        mSettings.setSupportZoom(true);
        mSettings.setBuiltInZoomControls(true);
        //不显示缩放按钮
        mSettings.setDisplayZoomControls(false);
        //设置自适应屏幕，两者合用
        //将图片调整到适合WebView的大小
        mSettings.setUseWideViewPort(true);
        //缩放至屏幕的大小
        mSettings.setLoadWithOverviewMode(true);
        //自适应屏幕
        mSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
        web_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (C.isLogin) {
                    if (collect) {
                        unCollect(id,originId);
                    } else {
                        Save(id);
                    }
                }else {
                    Intent intent = new Intent();
                    intent.setClass(WebActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
        register();
    }

    @SuppressLint("CheckResult")
    private void register(){
        RxBus.getDefault().toObservable(this,CollectEvent.class).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CollectEvent>() {
                    @Override
                    public void accept(CollectEvent collectEvent) throws Exception {
                        if (collect){
                            web_image.setImageResource(R.drawable.icon_like_article_not_selected);
                            collect = false;
                        }else {
                            web_image.setImageResource(R.drawable.icon_like_selected);
                            collect = true;
                        }

                    }
                });
    }
    private void Save(int id) {
        HttpHelperImp.httpHelperImp.SaveArticle(id)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResult baseResult) {
                        RxBus.getDefault().post(new CollectEvent());
                        Toast.makeText(WebActivity.this,"文章已收藏",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void unCollect(int id,int originId) {
        if (originId==-1) {
            HttpHelperImp.httpHelperImp.unCollect(id)
                    .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<BaseResult>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(BaseResult baseResult) {
                            RxBus.getDefault().post(new CollectEvent());
                            Toast.makeText(WebActivity.this, "取消收藏成功", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }else {
            HttpHelperImp.httpHelperImp.unCollectArticle(id,originId)
                    .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<BaseResult>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(BaseResult baseResult) {
                            RxBus.getDefault().post(new CollectEvent());
                            Toast.makeText(WebActivity.this, "取消收藏成功", Toast.LENGTH_SHORT).show();
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

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
