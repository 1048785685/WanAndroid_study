package com.example.liuyang05_sx.androidstudy.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.liuyang05_sx.androidstudy.R;
import com.example.liuyang05_sx.androidstudy.base.BaseActivity;
import com.example.liuyang05_sx.androidstudy.bean.BaseResult;
import com.example.liuyang05_sx.androidstudy.http.HttpHelperImp;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.register_button_login)
    Button register_button_login;
    @BindView(R.id.register_button_register)
    Button register_button_register;
    @BindView(R.id.register_edt_account)
    EditText register_edt_account;
    @BindView(R.id.register_edt_psw)
    EditText register_edt_psw;
    @BindView(R.id.register_edt_sure)
    EditText register_edt_sure;
    @BindView(R.id.register_toolbar)
    Toolbar register_toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        init();
        setSupportActionBar(register_toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        register_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void init() {

        register_button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });



        register_button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register(register_edt_account.getText().toString().trim(),
                        register_edt_psw.getText().toString().trim(),
                        register_edt_sure.getText().toString().trim());
            }
        });
    }
    public void register(String username, String password, String repassword){
        HttpHelperImp.httpHelperImp.Register(username,password,repassword)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResult baseResult) {
                        if (baseResult.getErrorCode()==-1){
                            Toast.makeText(RegisterActivity.this,baseResult.getErrorMsg(),Toast.LENGTH_SHORT).show();
                        }else {
                            finish();
                            Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
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
