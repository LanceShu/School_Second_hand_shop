package com.our_company.school_second_hand_shop.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.our_company.school_second_hand_shop.R;

/**
 * Created by Lance on 2017/3/19.
 */

public class Login_Activity extends AppCompatActivity implements View.OnClickListener{

    private String loginName;
    private String loginPass;
    private boolean isCanSee = false;

    ImageView login_back;           //返回键;
    ImageView login_person;         //用户的头像;
    EditText login_name;            //用户的手机号   ;
    EditText login_pass;            //用户的密码;
    Button login_enter;             //登录按钮;
    TextView login_forgetPass;      //忘记密码按钮;
    TextView login_apply;           //立即注册按钮;
    ImageView login_clear;
    ImageView login_isCanSee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_menu);
        init();
    }

    private void init() {
        login_back = (ImageView) findViewById(R.id.login_back);
        login_person = (ImageView) findViewById(R.id.login_person);
        login_name = (EditText) findViewById(R.id.login_name);
        login_pass = (EditText) findViewById(R.id.login_pass);
        login_enter = (Button) findViewById(R.id.login_enter);
        login_forgetPass = (TextView) findViewById(R.id.login_forgetPass);
        login_apply = (TextView) findViewById(R.id.login_apply);
        login_clear = (ImageView) findViewById(R.id.login_clear);
        login_isCanSee  = (ImageView) findViewById(R.id.login_isCanSee);

        login_back.setOnClickListener(this);
        login_enter.setOnClickListener(this);
        login_forgetPass.setOnClickListener(this);
        login_apply.setOnClickListener(this);
        login_clear.setOnClickListener(this);
        login_isCanSee.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_back:
                finish();
                break;
            case R.id.login_enter:
                //用户名与密码输入正确时，登录;
                if(true){

                }
                break;
            case R.id.login_forgetPass:
                Intent intentForget = new Intent(Login_Activity.this,ForgetPass_Activity.class);
                startActivity(intentForget);
                break;
            case R.id.login_apply:
                Intent intentApply = new Intent(Login_Activity.this,Register_Activity.class);
                startActivity(intentApply);
                break;
            case R.id.login_clear:
                login_name.getText().clear();
                break;
            case R.id.login_isCanSee:
                if(isCanSee){
                    login_isCanSee.setImageResource(R.mipmap.eye_cant_see);
                    isCanSee = false;
                    login_pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }else{
                    isCanSee = true;
                    login_isCanSee.setImageResource(R.mipmap.eyes_can_see);
                    login_pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                break;
            default:
                break;
        }
    }
}
