package com.hnvist.apptest1.ui;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.hnvist.apptest1.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_main);

        // 初始化代码
        initView();


    }

    void initView() {

    }

}
