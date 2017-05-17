package com.our_company.school_second_hand_shop.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.our_company.school_second_hand_shop.DataBase.MyDatabaseHelper;
import com.our_company.school_second_hand_shop.R;

import static com.our_company.school_second_hand_shop.Content.*;

/**
 * Created by Lance on 2017/5/9.
 */

public class UserInfo extends AppCompatActivity implements View.OnClickListener{

    private TextView name;
    private TextView sex;
    private TextView age;
    private TextView birth;
    private TextView number;
    private TextView email;
    private Button modify;
    private ImageView back;
    private MyDatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info);
        myDatabaseHelper = new MyDatabaseHelper(this,"PromissDB.db",null,2);
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case MODIFY_SUCCESS:
                        SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
                        Cursor cursor = db.query("Per",null,"num = "+ currentNum,null,null,null,null);
                        if(cursor.moveToFirst()){
                            do{
                                name.setText(cursor.getString(cursor.getColumnIndex("name")));
                                sex.setText(cursor.getString(cursor.getColumnIndex("sex")));
                                age.setText(cursor.getString(cursor.getColumnIndex("age")));
                                birth.setText(cursor.getString(cursor.getColumnIndex("birth")));
                                number.setText(cursor.getString(cursor.getColumnIndex("num")));
                                email.setText(cursor.getString(cursor.getColumnIndex("mail")));
                            }while(cursor.moveToNext());
                        }
                        cursor.close();
                        break;
                }
            }
        };
        initWight();
    }

    private void initWight() {
        name = (TextView) findViewById(R.id.name);
        sex = (TextView) findViewById(R.id.sex);
        age = (TextView) findViewById(R.id.age);
        birth = (TextView) findViewById(R.id.birth);
        number = (TextView) findViewById(R.id.number);
        email = (TextView) findViewById(R.id.mail);
        modify = (Button) findViewById(R.id.modefy);
        back = (ImageView) findViewById(R.id.register_layout_back_button);

        SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
        Cursor cursor = db.query("Per",null,"num = "+ currentNum,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                name.setText(cursor.getString(cursor.getColumnIndex("name")));
                sex.setText(cursor.getString(cursor.getColumnIndex("sex")));
                age.setText(cursor.getString(cursor.getColumnIndex("age")));
                birth.setText(cursor.getString(cursor.getColumnIndex("birth")));
                number.setText(cursor.getString(cursor.getColumnIndex("num")));
                email.setText(cursor.getString(cursor.getColumnIndex("mail")));
            }while(cursor.moveToNext());
        }
        cursor.close();

        modify.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.modefy:
                SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
                db.delete("Per","num = "+ currentNum,null);
                Intent intent = new Intent(this,UserInfoModify.class);
                startActivity(intent);
                break;
            case R.id.register_layout_back_button:
                finish();
                break;
            default:
                break;
        }
    }
}
