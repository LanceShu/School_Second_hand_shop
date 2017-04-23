package com.our_company.school_second_hand_shop.Activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.our_company.school_second_hand_shop.Interface.CountListener;
import com.our_company.school_second_hand_shop.OtherClass.CountClass;
import com.our_company.school_second_hand_shop.R;

/**
 * Created by LZL on 2017/3/19.
 */

public class Register_Activity extends AppCompatActivity implements View.OnClickListener {
    ImageView backbutton;
    EditText phonenumber_input;
    EditText password_input;
    EditText password_again_input;
    ImageView clearbutton;
    ImageView password_see;
    ImageView password_again_see;
    TextView get_secretcode_text;

    boolean pass_see = false;
    boolean pass_again_see = false;
    boolean count_change = false;

    int count = 60;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        init();
    }

    public void init()
    {
        bindingView();
        setOnclickListenerOnView();
    }

    public void bindingView()
    {
        //绑定控件函数
        backbutton = (ImageView)findViewById(R.id.register_layout_back_button);
        password_input = (EditText)findViewById(R.id.register_layout_password_input);
        password_again_input = (EditText)findViewById(R.id.register_layout_password_agin_input);
        phonenumber_input = (EditText)findViewById(R.id.register_layout_phonenumber_input);
        clearbutton = (ImageView)findViewById(R.id.register_layout_clear_button);
        password_see = (ImageView)findViewById(R.id.register_layout_see_password_button);
        password_again_see = (ImageView)findViewById(R.id.register_layout_see_password_again_button);
        get_secretcode_text = (TextView)findViewById(R.id.register_layout_secretcode_text);
    }

    public void setOnclickListenerOnView()
    {
        //设置监听函数
        backbutton.setOnClickListener(this);
        clearbutton.setOnClickListener(this);
        password_again_see.setOnClickListener(this);
        password_see.setOnClickListener(this);
        get_secretcode_text.setOnClickListener(this);
    }

    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.register_layout_back_button:
            {
                finish();
                break;
            }
            case R.id.register_layout_see_password_button:
            {
                if(pass_see)
                {
                    password_see.setImageResource(R.mipmap.eye_cant_see);
                    password_input.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    pass_see = false;
                }
                else
                {
                    password_see.setImageResource(R.mipmap.eyes_can_see);
                    password_input.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    pass_see = true;
                }
                break;
            }
            case R.id.register_layout_see_password_again_button:
            {
                if(pass_again_see)
                {
                    pass_again_see = false;
                    password_again_input.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    password_again_see.setImageResource(R.mipmap.eye_cant_see);
                }
                else
                {
                    pass_again_see = true;
                    password_again_input.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    password_again_see.setImageResource(R.mipmap.eyes_can_see);
                }
                break;
            }
            case R.id.register_layout_clear_button:
            {
                phonenumber_input.getText().clear();
                break;
            }

            case R.id.register_layout_secretcode_text:
            {
                if(!count_change)
                {
                    count_change = true;
                    CountClass.startCount(new CountListener() {
                        @Override
                        public void doCount(final int i) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    get_secretcode_text.setText("还有"+i+"s");
                                }
                            });
                        }

                        @Override
                        public void overCount() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    get_secretcode_text.setText("获取验证码");
                                    count_change = false;
                                }
                            });
                        }
                    });
                    break;
                }
            }
        }
    }

}