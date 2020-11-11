package com.hnvist.apptest1.ui.fragment;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.hnvist.apptest1.R;

public class SdkzFragment extends Fragment {

    Activity activity;
    View view;


    TextView text1, text2, text3, text4;
    ImageView img1, img2, img3, img4;
    Switch swh1, swh2, swh3, swh4;

    Boolean isFs = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_sdkz, container, false);
        initView();

        return view;
    }

    void initView() {

        text1 = view.findViewById(R.id.f_sdkz_text_1);
        text2 = view.findViewById(R.id.f_sdkz_text_2);
        text3 = view.findViewById(R.id.f_sdkz_text_3);
        text4 = view.findViewById(R.id.f_sdkz_text_4);

        img1 = view.findViewById(R.id.f_sdkz_img_1);
        img2 = view.findViewById(R.id.f_sdkz_img_2);
        img3 = view.findViewById(R.id.f_sdkz_img_3);
        img4 = view.findViewById(R.id.f_sdkz_img_4);

        swh1 = view.findViewById(R.id.f_sdkz_swh_1);
        swh2 = view.findViewById(R.id.f_sdkz_swh_2);
        swh3 = view.findViewById(R.id.f_sdkz_swh_3);
        swh4 = view.findViewById(R.id.f_sdkz_swh_4);

        swh1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setFs(isChecked);
            }
        });

        swh2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setYg(isChecked);
            }
        });

        swh3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setSy(isChecked);
            }
        });

        swh4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setFm(isChecked);
            }
        });


        // 初始化页面配置
        setFs(false);
        setYg(false);
        setSy(true);
        setFm(false);

        // cancelAuto(false);

    }

    void setFs(Boolean is) {
        if (text1 == null || img1 == null || swh1 == null) return; // 判断组件是否初始化，避免闪退

        text1.setText(is ? "开启" : "关闭");
        img1.setImageResource(is ? R.drawable.ic_sdkz_fsg : R.drawable.ic_sdkz_fsg_b);
        swh1.setChecked(is);
        swh1.setText(is ? "开启" : "关闭");
    }

    void setYg(Boolean is) {
        if (text2 == null || img2 == null || swh2 == null) return; // 判断组件是否初始化，避免闪退

        text2.setText(is ? "开启" : "关闭");
        img2.setImageResource(is ? R.drawable.ic_sdkz_ygd : R.drawable.ic_sdkz_ygd_b);
        swh2.setChecked(is);
        swh2.setText(is ? "开启" : "关闭");
    }

    void setSy(Boolean is) {
        if (text3 == null || img3 == null || swh3 == null) return; // 判断组件是否初始化，避免闪退

        text3.setText(is ? "开启" : "关闭");
        img3.setImageResource(is ? R.drawable.ic_sdkz_syt : R.drawable.ic_sdkz_syt_b);
        swh3.setChecked(is);
        swh3.setText(is ? "开启" : "关闭");
    }

    void setFm(Boolean is) {
        if (text4 == null || img4 == null || swh4 == null) return; // 判断组件是否初始化，避免闪退

        text4.setText(is ? "开启" : "关闭");
        img4.setImageResource(is ? R.drawable.ic_sdkz_fmq : R.drawable.ic_sdkz_fmq_b);
        swh4.setChecked(is);
        swh4.setText(is ? "开启" : "关闭");
    }

    void cancelAuto(Boolean is) {
        if (swh1 == null || swh2 == null || swh3 == null || swh4 == null) return;
        swh1.setClickable(is);
        swh2.setClickable(is);
        swh3.setClickable(is);
        swh4.setClickable(is);
    }

}