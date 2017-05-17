package com.our_company.school_second_hand_shop.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.our_company.school_second_hand_shop.R;

import org.w3c.dom.Text;

/**
 * Created by Lance on 2017/3/20.
 */

public class ForgetPass_Activity extends AppCompatActivity implements View.OnClickListener{

    private String num;                                     //存储手机号;
    private String code;                                    //存储验证码;

    private ImageView forgetPass_back;                      //返回键；
    private EditText forgetPass_num;                        //手机号码输入；
    private EditText forgetPass_code;                       //验证码输入;
    private TextView forgetPass_appleCode;                  //申请验证码的按钮;
    private Button forgetPass_next;                         //下一步的按钮;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_pass);
        init();
    }

    private void init() {
        forgetPass_back = (ImageView) findViewById(R.id.forgetPass_back);
        forgetPass_num = (EditText) findViewById(R.id.forgetPass_num);
        forgetPass_code = (EditText) findViewById(R.id.forgetPass_code);
        forgetPass_appleCode = (TextView) findViewById(R.id.forgetPass_appleCode);
        forgetPass_next = (Button) findViewById(R.id.forgetPass_next);

        forgetPass_back.setOnClickListener(this);
        forgetPass_appleCode.setOnClickListener(this);
        forgetPass_next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.forgetPass_back:
                finish();
                break;
            case R.id.forgetPass_appleCode:
                //申请验证码

                break;
            case R.id.forgetPass_next:
                //如果验证码与后台发来的相符合，则进入下一步操作;
                if(true){
                    Intent intent2 = new Intent(ForgetPass_Activity.this,ForgetPassNext_Activity.class);
                    startActivity(intent2);
                }
                break;
            default:
                break;
        }
    }
}
