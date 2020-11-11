package com.hnvist.apptest1.ui;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.hnvist.apptest1.BaseApplication;
import com.hnvist.apptest1.R;
import com.hnvist.apptest1.ui.fragment.HjzbFragment;
import com.hnvist.apptest1.ui.fragment.LssjFragment;
import com.hnvist.apptest1.ui.fragment.SdkzFragment;
import com.hnvist.apptest1.ui.fragment.XtkzFragment;


public class HomeActivity extends FragmentActivity implements View.OnClickListener {
    private FragmentManager manager;
    private Fragment[] fragments;
    private int lastIndex;

    private Fragment fragmentHjzb, fragmentLssj,fragmentSdkz,fragmentXtsz;
    private Button hjzb, lssj, sdkz, xtsz;

    static void start() {
        Intent intent = new Intent(BaseApplication.context, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        BaseApplication.context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initView();
    }



    private void initView() {
        hjzb = findViewById(R.id.home_hjzb);
        lssj = findViewById(R.id.home_lssj);
        sdkz = findViewById(R.id.home_sdkz);
        xtsz = findViewById(R.id.home_xtsz);

        fragmentHjzb = new HjzbFragment();
        fragmentLssj = new LssjFragment();
        fragmentSdkz = new SdkzFragment();
        fragmentXtsz = new XtkzFragment();

        manager = getSupportFragmentManager();
        fragments = new Fragment[]{fragmentHjzb, fragmentLssj, fragmentSdkz, fragmentXtsz};
        lastIndex = 2;

        manager.beginTransaction().add(R.id.fragmen_mian, fragments[lastIndex]).commit();

        hjzb.setOnClickListener(this);
        lssj.setOnClickListener(this);
        sdkz.setOnClickListener(this);
        xtsz.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home_hjzb:
                if (lastIndex != 0){
                    fragmentHide(lastIndex, 0);
                    lastIndex = 0;
                }
                break;
            case R.id.home_lssj:
                if (lastIndex != 1){
                    fragmentHide(lastIndex, 1);
                    lastIndex = 1;
                }
                break;
            case R.id.home_sdkz:
                if (lastIndex != 2){
                    fragmentHide(lastIndex, 2);
                    lastIndex = 2;
                }
                break;
            case R.id.home_xtsz:
                if (lastIndex != 3){
                    fragmentHide(lastIndex, 3);
                    lastIndex = 3;
                }
                break;
        }
    }

    private void fragmentHide(int last, int index){
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.hide(fragments[last]).commit();
        if (!fragments[index].isAdded()){
            transaction.add(R.id.fragmen_mian, fragments[index]);
        }
        transaction.show(fragments[index]);
    }
}