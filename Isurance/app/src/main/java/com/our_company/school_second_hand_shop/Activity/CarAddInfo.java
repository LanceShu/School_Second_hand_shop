package com.our_company.school_second_hand_shop.Activity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.our_company.school_second_hand_shop.DataBase.MyDatabaseHelper;
import com.our_company.school_second_hand_shop.R;
import static com.our_company.school_second_hand_shop.Content.*;

/**
 * Created by Lance on 2017/5/10.
 */

public class CarAddInfo extends AppCompatActivity implements View.OnClickListener {

    private EditText year;                      //购车年份;
    private EditText price;                     //购车价格；
    private EditText num;                       //车牌号；
    private Button add;                         //添加按钮；
    private ImageView back;                     //返回按钮；
    private MyDatabaseHelper myDatabaseHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car_add);
        myDatabaseHelper = new MyDatabaseHelper(this,"PromissDB.db",null,2);
        db = myDatabaseHelper.getWritableDatabase();
        initWight();
    }

    private void initWight() {
        year = (EditText) findViewById(R.id.car_year);
        price = (EditText) findViewById(R.id.car_price);
        num = (EditText) findViewById(R.id.car_num);
        add = (Button) findViewById(R.id.addcar);
        back = (ImageView) findViewById(R.id.register_layout_back_button);

        back.setOnClickListener(this);
        add.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addcar:
                if(num.getText().toString().length()!=7){
                    Toast.makeText(this,"车牌号格式有误",Toast.LENGTH_SHORT).show();
                }else{
                    ContentValues values = new ContentValues();
                    values.put("perNum",currentNum);
                    values.put("carYear",year.getText().toString());
                    values.put("carPrice",price.getText().toString());
                    values.put("carNum",num.getText().toString());
                    db.insert("Car",null,values);
                    Snackbar.make(add,"车辆信息添加成功！",Snackbar.LENGTH_SHORT).show();
                    Message message = new Message();
                    message.what = ADD_CARINFO;
                    handler.sendMessage(message);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    },1000);
                }
                break;
            case R.id.register_layout_back_button:
                finish();
                break;
        }
    }
}
