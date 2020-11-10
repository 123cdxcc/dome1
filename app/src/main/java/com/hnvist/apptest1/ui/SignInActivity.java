package com.hnvist.apptest1.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.hnvist.apptest1.BaseApplication;
import com.hnvist.apptest1.R;

public class SignInActivity extends Activity {

    Context mContext;

    EditText mEdt_zh, mEdt_mm;
    CheckBox mCbx_jzmm;
    Button mBtn_wjmm, mBtn_zczh, mBtn_qx, mBtn_dl, mBtn_sz;

    Boolean isRePasswd = false;

    String u_zh = "bin", u_mm = "123456";

    static void start() {
        Intent intent = new Intent(BaseApplication.context, SignInActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        BaseApplication.context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_main);

        // 初始化代码
        initView();

    }

    void initView() {
        mEdt_zh = findViewById(R.id.main_account);
        mEdt_mm = findViewById(R.id.main_password);
        mBtn_zczh = findViewById(R.id.main_zhzz);
        mBtn_wjmm = findViewById(R.id.main_wjmm);
        mBtn_qx = findViewById(R.id.main_qx);
        mBtn_dl = findViewById(R.id.main_dl);
        mBtn_sz = findViewById(R.id.main_sz);

        mCbx_jzmm = findViewById(R.id.main_jzmm);

        // 取消
        mBtn_qx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 登陆
        mBtn_dl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String zh = mEdt_zh.getText().toString();
                String mm = mEdt_mm.getText().toString();
                apiDl(zh, mm);

                if (isRePasswd) {
                    RePassword(zh, mm);
                }

            }
        });

        // 注册
        mBtn_zczh.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                onRegister();
            }
        });

        // 忘记密码
        mBtn_wjmm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String zh = mEdt_zh.getText().toString();
                apiWjmm(zh);
            }
        });

        // 设置记住密码状态
        isRePasswd = mCbx_jzmm.isChecked();
        mCbx_jzmm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRePasswd) rmRePassword();
                isRePasswd = !isRePasswd;
                mCbx_jzmm.setChecked(isRePasswd);
            }
        });

        // 读取记住密码数据
        readRePassword();

    }

    void apiDl(String zh, String mm) {

        if (zh.equals("") || mm.equals("")) {
            Toast.makeText(this, "账号或密码不得为空！", Toast.LENGTH_SHORT).show();
            return;
        } else if (zh.length() < 3) {
            Toast.makeText(this, "用户名不得小于 3 位！", Toast.LENGTH_SHORT).show();
            return;
        } else if (mm.length() < 3) {
            Toast.makeText(this, "密码不得小于 3 位！", Toast.LENGTH_SHORT).show();
            return;
        }

        if (zh.equals(u_zh) && mm.equals(u_mm)) {
            Toast.makeText(this, "登陆成功！", Toast.LENGTH_SHORT).show();
            HomeActivity.start();
            finish();
        } else {
            Toast.makeText(this, "用户名或密码错误！", Toast.LENGTH_SHORT).show();
        }

    }

    void apiWjmm(String zh) {
        if (zh.equals("")) {
            Toast.makeText(this, "请输入需要找回密码的账号！", Toast.LENGTH_SHORT).show();
            return;
        }

        if (zh.equals(u_zh)) {
            Toast.makeText(this, "用户名：" + zh + "，你的密码是：" + u_mm, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "未发现该用户，请先注册！", Toast.LENGTH_SHORT).show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    void onRegister() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_signup, null);

        builder.setTitle("用户注册")
                .setView(view)
                .setPositiveButton("注册", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText eID = view.findViewById(R.id.d_s_id);
                        EditText eEmail = view.findViewById(R.id.d_s_email);
                        EditText ePasswd = view.findViewById(R.id.d_s_passwd);

                        String mID = eID.getText().toString();
                        String mEmail = eEmail.getText().toString();
                        String mPasswd = ePasswd.getText().toString();

                        if (mID.length() < 1 || mEmail.length() < 1 || mPasswd.length() < 1) {
                            Toast.makeText(mContext, "用户名，邮箱，密码不得为空！", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        // 判断账号是否合法
                        if (!isZHHefa(mID)) return;

                        // 判断邮箱是否合法
                        if (!isEMHefa(mEmail)) return;

                        // 判断密码是否合法
                        if (!isMMHefa(mPasswd)) return;

                        // 请求账号注册接口
                        apiZczh(mID, mPasswd, mEmail);

                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel(); // 取消注册，关闭注册框
                    }
                });

        builder.show();

    }

    void apiZczh(String zh, String mm, String email) {
        Toast.makeText(mContext, zh + "账号注册成功！", Toast.LENGTH_SHORT).show();
    }

    // 判断账号是否合法
    Boolean isZHHefa(String zh) {

        if (zh.length() < 6) {
            Toast.makeText(this, "账号不得小于 6 位！", Toast.LENGTH_SHORT).show();
            return false;
        } else if (zh.length() > 12) {
            Toast.makeText(this, "账号不得大于 12 位！", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!zh.matches("[a-zA-Z]+")) {
            Toast.makeText(this, "账号必须为纯字母，忽略大小写！", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    // 判断账号是否合法
    Boolean isMMHefa(String mm) {

        if (mm.length() < 3) {
            Toast.makeText(this, "密码不得小于 3 位！", Toast.LENGTH_SHORT).show();
            return false;
        } else if (mm.length() > 6) {
            Toast.makeText(this, "密码不得大于 6 位！", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!mm.matches(".*[a-zA-Z].*") || !mm.matches(".*[0-9].*")) {
            Toast.makeText(this, "密码必须为字母与数字组合！", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    // 判断邮箱是否合法
    Boolean isEMHefa(String em) {

        if (!em.matches(".*[@].*[.].*")) {
            Toast.makeText(this, "邮箱地址输入错误！", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }


    void RePassword(String zh, String mm) {
        SharedPreferences sharedPreferences = this.getSharedPreferences("UserRePasswd", Context.MODE_PRIVATE);
        SharedPreferences.Editor sEdit = sharedPreferences.edit();
        sEdit.putString("user_name", zh);
        sEdit.putString("user_passwd", mm);
        sEdit.apply();
    }

    void rmRePassword() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("UserRePasswd", Context.MODE_PRIVATE);
        SharedPreferences.Editor sEdit = sharedPreferences.edit();
        sEdit.remove("user_name");
        sEdit.remove("user_passwd");
        sEdit.apply();
    }

    void readRePassword() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("UserRePasswd", Context.MODE_PRIVATE);
        String zh = sharedPreferences.getString("user_name", "");
        String mm = sharedPreferences.getString("user_passwd", "");

        if (!zh.equals("") && !mm.equals("")) {
            mEdt_zh.setText(zh);
            mEdt_mm.setText(mm);

            isRePasswd = true;
            mCbx_jzmm.setChecked(true);
        }

    }


}
