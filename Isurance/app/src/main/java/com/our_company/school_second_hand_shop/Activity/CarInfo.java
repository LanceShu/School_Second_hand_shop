package com.our_company.school_second_hand_shop.Activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.our_company.school_second_hand_shop.DataBase.MyDatabaseHelper;
import com.our_company.school_second_hand_shop.DataClass.CarData;
import com.our_company.school_second_hand_shop.MyAdapter.CarAdapter;
import com.our_company.school_second_hand_shop.R;

import java.util.ArrayList;
import java.util.List;

import static com.our_company.school_second_hand_shop.Content.ADD_CARINFO;
import static com.our_company.school_second_hand_shop.Content.currentNum;
import static com.our_company.school_second_hand_shop.Content.handler;

/**
 * Created by Lance on 2017/5/10.
 */

public class CarInfo extends AppCompatActivity implements View.OnClickListener{

    private ImageView back;
    private RecyclerView carList;
    private Button add;
    private MyDatabaseHelper myDatabaseHelper;
    private SQLiteDatabase db;

    private List<CarData> carDataList;
    private CarAdapter carAdapter;
    private LinearLayoutManager linearLayoutManager;
    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car_info);
        context = this;
        carDataList = new ArrayList<>();
        myDatabaseHelper = new MyDatabaseHelper(this,"PromissDB.db",null,2);
        db = myDatabaseHelper.getWritableDatabase();
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case ADD_CARINFO:
                        carDataList.clear();
                        Cursor cursor = db.query("Car",null,"perNum = "+ currentNum ,null,null,null,null);
                        if(cursor.moveToFirst()){
                            do{
                                String year = cursor.getString(cursor.getColumnIndex("carYear"));
                                String price = cursor.getString(cursor.getColumnIndex("carPrice"));
                                String num = cursor.getString(cursor.getColumnIndex("carNum"));
                                CarData carData = new CarData(year,price,num);
                                carDataList.add(carData);
                            }while(cursor.moveToNext());
                        }
                        cursor.close();
                        carAdapter = new CarAdapter(CarInfo.this,carDataList);
                        carList.setAdapter(carAdapter);
                        break;
                }
            }
        };
        initWight();
    }

    private void initWight() {
        back = (ImageView) findViewById(R.id.register_layout_back_button);
        carList = (RecyclerView) findViewById(R.id.car_list);
        add  = (Button) findViewById(R.id.addcar);

        back.setOnClickListener(this);
        add.setOnClickListener(this);

        Cursor cursor = db.query("Car",null,"perNum = "+ currentNum ,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                String year = cursor.getString(cursor.getColumnIndex("carYear"));
                String price = cursor.getString(cursor.getColumnIndex("carPrice"));
                String num = cursor.getString(cursor.getColumnIndex("carNum"));
                CarData carData = new CarData(year,price,num);
                carDataList.add(carData);
            }while(cursor.moveToNext());
        }
        cursor.close();
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        carList.setLayoutManager(linearLayoutManager);
        carAdapter = new CarAdapter(this,carDataList);
        carList.setAdapter(carAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_layout_back_button:
                finish();
                break;
            case R.id.addcar:
                Intent intent = new Intent(this,CarAddInfo.class);
                startActivity(intent);
                break;
        }
    }
}
