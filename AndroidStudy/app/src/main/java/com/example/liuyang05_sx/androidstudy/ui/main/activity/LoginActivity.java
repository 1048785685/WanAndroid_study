package com.example.liuyang05_sx.androidstudy.ui.main.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.liuyang05_sx.androidstudy.R;
import com.example.liuyang05_sx.androidstudy.base.BaseActivity;
import com.example.liuyang05_sx.androidstudy.bean.BaseResult;
import com.example.liuyang05_sx.androidstudy.bean.event.LoginEvent;
import com.example.liuyang05_sx.androidstudy.bean.main.LoginData;
import com.example.liuyang05_sx.androidstudy.http.HttpHelperImp;
import com.example.liuyang05_sx.androidstudy.utils.ACache;
import com.example.liuyang05_sx.androidstudy.utils.C;
import com.example.liuyang05_sx.androidstudy.utils.RxBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.login_toolbar)
    Toolbar login_toolbar;
    @BindView(R.id.login_account_edit)
    EditText login_account_edit;
    @BindView(R.id.login_password_edit)
    EditText login_password_edit;
    @BindView(R.id.login_button_login)
    Button login_button_login;
    @BindView(R.id.login_button_register)
    Button login_button_register;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        init();

    }

    private void init() {

        setSupportActionBar(login_toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        login_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        login_button_login.setOnClickListener(this);
        login_button_register.setOnClickListener(this);
    }



    @SuppressLint({"ResourceAsColor", "NewApi"})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case    R.id.login_button_login:
                if (!login_account_edit.getText().toString().trim().equals("") &&
                        !login_password_edit.getText().toString().trim().equals("")){
                    Login();
                }else {
                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),"账号或密码不能为空",Snackbar.LENGTH_LONG);
                snackbar.getView().setBackground(getDrawable(R.color.blue));
                ((TextView)snackbar.getView().findViewById(R.id.snackbar_text)).setTextColor(R.color.white);
                snackbar.show();
                }
                break;

            case    R.id.login_button_register:
                Intent intent = new Intent();
                intent.setClass(this,RegisterActivity.class);
                startActivity(intent);
                break;


        }
    }

    private void Login() {
        HttpHelperImp.httpHelperImp.Login_in(login_account_edit.getText().toString().trim(),
                login_password_edit.getText().toString().trim())
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResult<LoginData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResult<LoginData> loginDataBaseResult){
                        if (loginDataBaseResult.getErrorCode()==0){
                            RxBus.getDefault().post(new LoginEvent(true,loginDataBaseResult.getData().getUsername()));
                            ACache.get(LoginActivity.this).put(C.UserName,loginDataBaseResult.getData().getUsername());
                            ACache.get(LoginActivity.this).put(C.PassWord,loginDataBaseResult.getData().getPassword());
                            finish();
                        }else {
                            Toast.makeText(LoginActivity.this,loginDataBaseResult.getErrorMsg(),Toast.LENGTH_SHORT).show();
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
