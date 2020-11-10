package com.hnvist.apptest1.ui.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hnvist.apptest1.BaseApplication;
import com.hnvist.apptest1.R;

public class XtkzFragment extends Fragment {

    Activity activity;
    View rootView;
    LinearLayout m_yy, m_kzfs, m_kqwd, m_trwd, m_gzqd, m_co2ld;
    TextView m_yy_t, m_kzfs_t;

    boolean is_yy_cn = true;
    boolean is_auto_kz = false;

    String data_kqfz = "20";
    String data_trfz = "8";
    String data_gzfz = "66";
    String data_cofz = "35";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.fragment_xtkz, container, false);
        initView();
        return rootView;
    }

    void initView() {
        m_yy = rootView.findViewById(R.id.f_xtkz_yy);
        m_kzfs = rootView.findViewById(R.id.f_xtkz_kzfs);
        m_kqwd = rootView.findViewById(R.id.f_xtkz_kqwd);
        m_trwd = rootView.findViewById(R.id.f_xtkz_trwd);
        m_gzqd = rootView.findViewById(R.id.f_xtkz_gzqd);
        m_co2ld = rootView.findViewById(R.id.f_xtkz_co2ld);

        m_yy_t = rootView.findViewById(R.id.f_xtkz_yyt);
        m_kzfs_t = rootView.findViewById(R.id.f_xtkz_kzfst);

        // 语言设置点击事件
        showYYsz();
        m_yy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                is_yy_cn = !is_yy_cn;
                showYYsz();
            }
        });

        // 控制方式设置点击事件
        showKZsz();
        m_kzfs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                is_auto_kz = !is_auto_kz;
                showKZsz();
            }
        });

        // 空气阀值设置
        m_kqwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSetKqwd();
            }
        });

        // 土壤阀值设置
        m_trwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSetTrwd();
            }
        });

        // 光照阀值设置
        m_gzqd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSetGzwd();
            }
        });

        // CO2 阀值设置
        m_co2ld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSetCowd();
            }
        });


    }

    void showYYsz() {
        if (m_yy_t == null) return; // 判空防止闪退
        if (is_yy_cn) {
            m_yy_t.setText("中文");
        } else {
            m_yy_t.setText("英文");
        }
    }

    void showKZsz() {
        if (m_kzfs_t == null) return; // 判空防止闪退
        if (is_auto_kz) {
            m_kzfs_t.setText("自动");
        } else {
            m_kzfs_t.setText("手动");
        }
    }

    void showSetKqwd() {

        AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
        LayoutInflater layoutInflater = getLayoutInflater();

        View view = layoutInflater.inflate(R.layout.dialog_xtkz_kqkz, null);
        TextView dqz_t, dqzt_t, dqsz_t;
        EditText sz_e;

        dqz_t = view.findViewById(R.id.d_kqkz_t_dqz);
        dqzt_t = view.findViewById(R.id.d_kqkz_t_dzzt);
        dqsz_t = view.findViewById(R.id.d_kqkz_t_dqsz);
        sz_e = view.findViewById(R.id.d_kqkz_edit);

        dqz_t.setText("16");
        dqzt_t.setText("良好");
        dqsz_t.setText(data_kqfz);
        sz_e.setText(data_kqfz);

        dialog.setView(view)
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String val = sz_e.getText().toString();
                        data_kqfz = val;
                        dqsz_t.setText(data_kqfz);
                        sz_e.setText(data_kqfz);
                    }
                });
        dialog.show();


    }

    void showSetTrwd() {

        AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
        LayoutInflater layoutInflater = getLayoutInflater();

        View view = layoutInflater.inflate(R.layout.dialog_xtkz_trkz, null);
        TextView dqz_t, dqzt_t, dqsz_t;
        EditText sz_e;

        dqz_t = view.findViewById(R.id.d_trkz_t_dqz);
        dqzt_t = view.findViewById(R.id.d_trkz_t_dzzt);
        dqsz_t = view.findViewById(R.id.d_trkz_t_dqsz);
        sz_e = view.findViewById(R.id.d_trkz_edit);

        dqz_t.setText("16");
        dqzt_t.setText("良好");
        dqsz_t.setText(data_trfz);
        sz_e.setText(data_trfz);

        dialog.setView(view)
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String val = sz_e.getText().toString();
                        data_trfz = val;
                        dqsz_t.setText(data_trfz);
                        sz_e.setText(data_trfz);
                    }
                });
        dialog.show();


    }

    void showSetGzwd() {

        AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
        LayoutInflater layoutInflater = getLayoutInflater();

        View view = layoutInflater.inflate(R.layout.dialog_xtkz_gzkz, null);
        TextView dqz_t, dqzt_t, dqsz_t;
        EditText sz_e;

        dqz_t = view.findViewById(R.id.d_gzkz_t_dqz);
        dqzt_t = view.findViewById(R.id.d_gzkz_t_dzzt);
        dqsz_t = view.findViewById(R.id.d_gzkz_t_dqsz);
        sz_e = view.findViewById(R.id.d_gzkz_edit);

        dqz_t.setText("16");
        dqzt_t.setText("良好");
        dqsz_t.setText(data_gzfz);
        sz_e.setText(data_gzfz);

        dialog.setView(view)
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String val = sz_e.getText().toString();
                        data_gzfz = val;
                        dqsz_t.setText(data_gzfz);
                        sz_e.setText(data_gzfz);
                    }
                });
        dialog.show();


    }

    void showSetCowd() {

        AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
        LayoutInflater layoutInflater = getLayoutInflater();

        View view = layoutInflater.inflate(R.layout.dialog_xtkz_cokz, null);
        TextView dqz_t, dqzt_t, dqsz_t;
        EditText sz_e;

        dqz_t = view.findViewById(R.id.d_cokz_t_dqz);
        dqzt_t = view.findViewById(R.id.d_cokz_t_dzzt);
        dqsz_t = view.findViewById(R.id.d_cokz_t_dqsz);
        sz_e = view.findViewById(R.id.d_cokz_edit);

        dqz_t.setText("16");
        dqzt_t.setText("正常");
        dqsz_t.setText(data_cofz);
        sz_e.setText(data_cofz);

        dialog.setView(view)
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String val = sz_e.getText().toString();
                        data_cofz = val;
                        dqsz_t.setText(data_cofz);
                        sz_e.setText(data_cofz);
                    }
                });
        dialog.show();


    }


    void apiSetKqwd(String val) {

    }

}