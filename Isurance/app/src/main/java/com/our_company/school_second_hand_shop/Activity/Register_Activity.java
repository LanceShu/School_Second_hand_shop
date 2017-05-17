package com.our_company.school_second_hand_shop.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.our_company.school_second_hand_shop.DataBase.MyDatabaseHelper;
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
    Button register;

    boolean pass_see = false;
    boolean pass_again_see = false;
    boolean count_change = false;

    int count = 60;
    MyDatabaseHelper myDatabaseHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        myDatabaseHelper = new MyDatabaseHelper(this,"PromissDB.db",null,2);
        db = myDatabaseHelper.getWritableDatabase();
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
        password_input = (EditText)findViewById(R.id.password);
        password_again_input = (EditText)findViewById(R.id.measure_password);
        phonenumber_input = (EditText)findViewById(R.id.number);
        clearbutton = (ImageView)findViewById(R.id.register_layout_clear_button);
        password_see = (ImageView)findViewById(R.id.register_layout_see_password_button);
        password_again_see = (ImageView)findViewById(R.id.register_layout_see_password_again_button);
        register = (Button) findViewById(R.id.register);
    }

    public void setOnclickListenerOnView()
    {
        //设置监听函数
        backbutton.setOnClickListener(this);
        clearbutton.setOnClickListener(this);
        password_again_see.setOnClickListener(this);
        password_see.setOnClickListener(this);
        register.setOnClickListener(this);
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
            case R.id.register:
                Cursor cursor = db.query("Per",null,"num = "+ phonenumber_input.getText().toString(),null,null,null,null);
                if(cursor.moveToFirst()){
                    Toast.makeText(this,"该用户已存在",Toast.LENGTH_SHORT).show();
                }else{
                    if(password_again_input.getText().toString().equals(password_input.getText().toString())){
                        Intent intent = new Intent(this,UserInfoRegister.class);
                        intent.putExtra("num",phonenumber_input.getText().toString());
                        intent.putExtra("password",password_input.getText().toString());
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(this,"两次密码输入不一致.",Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

}