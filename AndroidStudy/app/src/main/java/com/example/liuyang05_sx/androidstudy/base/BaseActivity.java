package com.example.liuyang05_sx.androidstudy.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.example.liuyang05_sx.androidstudy.R;
import com.example.liuyang05_sx.androidstudy.http.RetrofitSingleton;
import com.readystatesoftware.systembartint.SystemBarTintManager;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setStatusBarColor(R.color.colorPrimaryDark);
    }

    private void setStatusBarColor(int color) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintResource(color);
        tintManager.setStatusBarTintEnabled(true);
    }
}
