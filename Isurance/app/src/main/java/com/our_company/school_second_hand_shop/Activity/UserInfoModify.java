package com.our_company.school_second_hand_shop.Activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.our_company.school_second_hand_shop.DataBase.MyDatabaseHelper;
import com.our_company.school_second_hand_shop.R;

import static com.our_company.school_second_hand_shop.Content.currentNum;
import static com.our_company.school_second_hand_shop.Content.*;

/**
 * Created by Lance on 2017/5/9.
 */

public class UserInfoModify extends AppCompatActivity implements View.OnClickListener{

    private EditText name;
    private EditText sex;
    private EditText age;
    private EditText birthYear;
    private EditText birthMonth;
    private EditText birthDay;
    private TextView number;
    private EditText email;
    private Button modify;
    private ImageView back;

    private MyDatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info_modify);
        myDatabaseHelper = new MyDatabaseHelper(this,"PromissDB.db",null,2);
        initWight();
    }

    private void initWight() {
        name = (EditText) findViewById(R.id.name);
        sex = (EditText) findViewById(R.id.sex);
        age = (EditText) findViewById(R.id.age);
        birthYear = (EditText) findViewById(R.id.birthyear);
        birthMonth = (EditText) findViewById(R.id.birthmonth);
        birthDay  = (EditText) findViewById(R.id.birthday);
        number = (TextView) findViewById(R.id.number);
        email = (EditText) findViewById(R.id.mail);
        modify = (Button) findViewById(R.id.modefy);
        back = (ImageView) findViewById(R.id.register_layout_back_button);

        number.setText(currentNum);
        modify.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.modefy:
                SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("name",name.getText().toString());
                values.put("pass",currentPass);
                values.put("num",currentNum);
                values.put("age",age.getText().toString());
                values.put("sex",sex.getText().toString());
                values.put("birth",birthYear.getText().toString()+"-"+birthMonth.getText().toString()+"-"+birthDay.getText().toString());
                values.put("mail",email.getText().toString());
                db.insert("Per",null,values);
                Cursor cursor = db.query("Per",null,null,null,null,null,null);
                if(cursor.moveToFirst()){
                    do{
                        String pass = cursor.getString(cursor.getColumnIndex("pass"));
                        Log.e("insert",pass);
                    }while (cursor.moveToNext());
                }
                cursor.close();
                Snackbar.make(modify,"完成修改！",Snackbar.LENGTH_SHORT).show();
                Message message = new Message();
                message.what = MODIFY_SUCCESS;
                handler.sendMessage(message);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                },1000);
                break;
            case R.id.register_layout_back_button:
                finish();
                break;
            default:
                break;
        }
    }
}
