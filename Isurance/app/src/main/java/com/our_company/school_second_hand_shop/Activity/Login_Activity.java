package com.our_company.school_second_hand_shop.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.our_company.school_second_hand_shop.DataBase.MyDatabaseHelper;
import com.our_company.school_second_hand_shop.R;

import static com.our_company.school_second_hand_shop.Content.*;

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
    TextView login_apply;           //立即注册按钮;
    ImageView login_clear;
    ImageView login_isCanSee;
    MyDatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_menu);
        myDatabaseHelper = new MyDatabaseHelper(this,"PromissDB.db",null,2);
        init();
    }

    private void init() {
        login_back = (ImageView) findViewById(R.id.login_back);
        login_person = (ImageView) findViewById(R.id.login_person);
        login_name = (EditText) findViewById(R.id.login_name);
        login_pass = (EditText) findViewById(R.id.login_pass);
        login_enter = (Button) findViewById(R.id.login_enter);
        login_apply = (TextView) findViewById(R.id.login_apply);
        login_clear = (ImageView) findViewById(R.id.login_clear);
        login_isCanSee  = (ImageView) findViewById(R.id.login_isCanSee);

        login_back.setOnClickListener(this);
        login_enter.setOnClickListener(this);
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
                SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
                String name = login_name.getText().toString();
                Cursor cursor = db.query("Per",null,"num = "+ name,null,null,null,null);
                if(cursor.moveToFirst()){
                    Log.e("join in ","success");
                    do{
                        String password = cursor.getString(cursor.getColumnIndex("pass"));
                        Log.e("password",password);
                        if(password.equals(login_pass.getText().toString())){
                            currentNum = login_name.getText().toString();
                            currentPass = login_pass.getText().toString();
                            Snackbar.make(login_enter,"登陆成功！",Snackbar.LENGTH_SHORT).show();
                            isLoginIn  = 1;
                            Message mess = new Message();
                            mess.what = LOGININ_SUCCESS;
                            handler.sendMessage(mess);
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    finish();
                                }
                            },1000);
                        }else {
                            Toast.makeText(this,"账号密码不正确,请重新输入.",Toast.LENGTH_SHORT).show();
                        }
                    }while(cursor.moveToNext());
                    cursor.close();
                }else{
                    Toast.makeText(this,"用户不存在",Toast.LENGTH_SHORT).show();
                }
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
