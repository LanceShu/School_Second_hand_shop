package com.our_company.school_second_hand_shop.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.our_company.school_second_hand_shop.R;

/**
 * Created by Lance on 2017/3/20.
 */

public class ForgetPassNext_Activity extends AppCompatActivity implements View.OnClickListener{

    private ImageView forgetPassNext_back;                  //返回键;
    private EditText getForgetPassNext_newPass;             //输入新密码;
    private EditText getForgetPassNext_newPassAgain;        //再次输入新密码;
    private Button getForgetPassNext_complete;              //完成修改的按钮;
    private ImageView forgetPass_newPassEye;
    private ImageView forgetPass_newPassAgainEye;

    private String newPass;
    private String newPassAgain;
    private boolean newPassEye = false;
    private boolean newPassAgainEye = false;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_pass_next);
        init();
    }

    private void init() {
        forgetPassNext_back = (ImageView) findViewById(R.id.forgetPassNext_back);
        getForgetPassNext_newPass = (EditText) findViewById(R.id.forgetPassNext_newPass);
        getForgetPassNext_newPassAgain = (EditText) findViewById(R.id.forgetPassNext_newPassAgain);
        getForgetPassNext_complete = (Button) findViewById(R.id.forgetPassNext_complete);
        forgetPass_newPassEye = (ImageView) findViewById(R.id.forgetPass_newPassEye);
        forgetPass_newPassAgainEye = (ImageView) findViewById(R.id.forgetPass_newPassAgainEye);

        forgetPassNext_back.setOnClickListener(this);
        getForgetPassNext_complete.setOnClickListener(this);
        forgetPass_newPassEye.setOnClickListener(this);
        forgetPass_newPassAgainEye.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.forgetPassNext_back:
                finish();
                break;
            case R.id.forgetPassNext_complete:
                newPass = getForgetPassNext_newPass.getText().toString();
                newPassAgain = getForgetPassNext_newPassAgain.getText().toString();

                //发送请求到服务器进行账号注册;

                Intent intent2 = new Intent(ForgetPassNext_Activity.this,Login_Activity.class);
                startActivity(intent2);
                break;
            case R.id.forgetPass_newPassEye:
                if(newPassEye){
                    newPassEye = false;
                    forgetPass_newPassEye.setImageResource(R.mipmap.eye_cant_see);
                    getForgetPassNext_newPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }else{
                    newPassEye = true;
                    forgetPass_newPassEye.setImageResource(R.mipmap.eyes_can_see);
                    getForgetPassNext_newPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                break;
            case R.id.forgetPass_newPassAgainEye:
                if(newPassAgainEye){
                    newPassAgainEye = false;
                    forgetPass_newPassAgainEye.setImageResource(R.mipmap.eye_cant_see);
                    getForgetPassNext_newPassAgain.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }else{
                    newPassAgainEye = true;
                    forgetPass_newPassAgainEye.setImageResource(R.mipmap.eyes_can_see);
                    getForgetPassNext_newPassAgain.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                break;
            default:
                break;
        }
    }
}
