package com.hnvist.apptest1.ui;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.hnvist.apptest1.BaseApplication;
import com.hnvist.apptest1.R;
import com.hnvist.apptest1.ui.fragment.HjzbFragment;
import com.hnvist.apptest1.ui.fragment.LssjFragment;
import com.hnvist.apptest1.ui.fragment.SdkzFragment;
import com.hnvist.apptest1.ui.fragment.XtkzFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends FragmentActivity implements View.OnClickListener {
    private List<Fragment> mList;
    private int fragmenIndex;
    private FragmentManager manager;
    private FragmentTransaction transaction;

    private Fragment fragmentHjzb, fragmentLssj,fragmentSdkz,fragmentXtsz;
    private FrameLayout fragmentMain;
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
        fragmentMain = findViewById(R.id.fragmen_mian);

        hjzb = findViewById(R.id.home_hjzb);
        lssj = findViewById(R.id.home_lssj);
        sdkz = findViewById(R.id.home_sdkz);
        xtsz = findViewById(R.id.home_xtsz);


        fragmentLssj = new LssjFragment();
        fragmentSdkz = new SdkzFragment();
        fragmentXtsz = new XtkzFragment();

        mList = new ArrayList<>();
        fragmenIndex = 0;
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.home_hjzb:
                if (fragmentHjzb == null){
                    fragmentHjzb = new HjzbFragment();
                    transaction.add(R.id.fragmen_mian, fragmentHjzb);
                    //fragmentShowOrHide(fragmentHjzb);
                } else {
                    transaction.show(fragmentHjzb);
                }
                break;
            case R.id.home_lssj:

                break;
            case R.id.home_sdkz:

                break;
            case R.id.home_xtsz:

                break;
        }
    }
    private void fragmentShowOrHide(Fragment currentFragment, boolean flag){

    }
}