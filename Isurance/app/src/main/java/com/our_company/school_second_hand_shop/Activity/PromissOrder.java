package com.our_company.school_second_hand_shop.Activity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.Slide;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.our_company.school_second_hand_shop.DataBase.MyDatabaseHelper;
import com.our_company.school_second_hand_shop.DataClass.CarData;
import com.our_company.school_second_hand_shop.MyAdapter.CarAdapter;
import com.our_company.school_second_hand_shop.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.our_company.school_second_hand_shop.Content.*;

/**
 * Created by Lance on 2017/4/19.
 */

public class PromissOrder extends AppCompatActivity implements View.OnClickListener,TextWatcher{

    private ImageView promiss_image;
    private TextView promiss_name;
    private RecyclerView recyclerView;
    private TextView car_year;
    private TextView car_price;
    private TextView car_num;
    private TextView promiss_price;
    private EditText promiss_year;
    private EditText promiss_money;
    private Button promiss_buy;
    private ImageView back;
    private TextView isHasCar;

    private int pos;
    private int imageId;
    private String promissName;
    private String carYear;
    private String carPrice;
    private String carNum;
    private String carPromissPeople;
    private String carPromissShoper;
    private String carPromissPrice;
    private String carPromissYear;
    private String carPromissMoney;
    private String promissPercent;

    private MyDatabaseHelper myDatabaseHelper;
    private SQLiteDatabase db;
    private List<CarData> carDataList;
    private CarAdapter carAdapter;
    private LinearLayoutManager linearLayoutManager;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        Slide fade = new Slide();
        getWindow().setEnterTransition(fade);
        fade.setDuration(500);
        setContentView(R.layout.promiss_order);
        myDatabaseHelper = new MyDatabaseHelper(this,"PromissDB.db",null,2);
        db = myDatabaseHelper.getWritableDatabase();
        carDataList = new ArrayList<>();
        getData();          //得到上个activity传递过来的数据;
        initWidght();       //实例化控件;

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case CHANGE_CAR:
                        car_year.setText(carDataList.get(carPosition).year);
                        car_price.setText(carDataList.get(carPosition).price);
                        car_num.setText(carDataList.get(carPosition).num);
                        double carP = Double.parseDouble(car_price.getText().toString());
                        double carY = Calendar.getInstance().get(Calendar.YEAR) - Double.parseDouble(car_year.getText().toString());
                        if(pos == 2){
                            if(carP <= 5){
                                promiss_year.setHint("626元/年");
                                promissPercent = "626元/年";
                            }else if(carP <= 10){
                                promiss_year.setHint("903元/年");
                                promissPercent = "903元/年";
                            }else if(carP <= 15){
                                promiss_year.setHint("1031元/年");
                                promissPercent = "1031元/年";
                            }else if(carP <= 20){
                                promiss_year.setHint("1120元/年");
                                promissPercent = "1120元/年";
                            }else if(carP <= 30){
                                promiss_year.setHint("1264元/年");
                                promissPercent = "1264元/年";
                            }else if(carP <= 50){
                                promiss_year.setHint("1516元/年");
                                promissPercent = "1516元/年";
                            }else{
                                promiss_year.setHint("1976元/年");
                                promissPercent = "1976元/年";
                            }
                        }else if(pos == 3){
                            if(carY <= 1){
                                promissPercent = "593+车价*1.41%";
                                promiss_year.setHint(promissPercent);
                            }else if(carY <= 2){
                                promissPercent = "564+车价*1.34%";
                                promiss_year.setHint(promissPercent);
                            }else if(carY <= 6){
                                promissPercent = "559+车价*1.33%";
                                promiss_year.setHint(promissPercent);
                            }else{
                                promissPercent = "576+车价*1.37%";
                                promiss_year.setHint(promissPercent);
                            }
                        }else if(pos == 4){
                            promissPercent = "(损失险+第三责任险)*20%";
                            promiss_year.setHint(promissPercent);
                        }else if(pos == 5){
                            promissPercent = "X*0.345%";
                            promiss_year.setHint(promissPercent);
                        }else if(pos == 6){
                            promissPercent = "120+X*0.41%";
                            promiss_year.setHint(promissPercent);
                        }
                        break;
                }
            }
        };
    }

    private void initWidght() {
        back = (ImageView) findViewById(R.id.goodsBack);
        promiss_image = (ImageView) findViewById(R.id.promiss_image);
        promiss_name = (TextView) findViewById(R.id.promiss_name);
        recyclerView = (RecyclerView) findViewById(R.id.car_list);
        car_year = (TextView) findViewById(R.id.car_year);
        car_price = (TextView) findViewById(R.id.car_price);
        car_num = (TextView) findViewById(R.id.car_num);
        promiss_price = (TextView) findViewById(R.id.promiss_price);
        promiss_year = (EditText) findViewById(R.id.promiss_year);
        promiss_money = (EditText) findViewById(R.id.promiss_money);
        promiss_buy = (Button) findViewById(R.id.buy_buy);
        isHasCar = (TextView) findViewById(R.id.isHasCar);

        back.setOnClickListener(this);
        promiss_buy.setOnClickListener(this);
        if(pos == 1){
            promiss_money.setText("122000");
            promiss_money.setEnabled(false);
            promiss_year.setHint("665元/年");
            promissPercent = "665元/年";
            carPromissMoney = "122000元/年";
        }else{
            promiss_money.setHint("万/元");
            promiss_money.setEnabled(true);

        }
        promiss_year.addTextChangedListener(this);

        promiss_image.setImageResource(imageId);
        promiss_name.setText(promissName);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        Log.e("carList.size",carDataList.size()+"");
        if(carDataList.size()>0){
            isHasCar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            carAdapter = new CarAdapter(this,carDataList);
            recyclerView.setAdapter(carAdapter);
        }else{
            isHasCar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }

    }

    private void getData() {
        Intent intent = getIntent();
        pos = intent.getIntExtra("position",-1);
        if(pos == 1){
            imageId  = R.mipmap.endowment;
            promissName = "交强险";
        }else if(pos == 2){
            imageId = R.mipmap.medical;
            promissName = "第三方责任险";
        }else if(pos == 3){
            imageId = R.mipmap.property;
            promissName = "车辆损失险";
        }else if(pos == 4){
            imageId = R.mipmap.life;
            promissName = "不计免赔险";
        }else if(pos == 5){
            imageId = R.mipmap.occupational;
            promissName = "车上人员责任险";
        }else if(pos == 6){
            imageId = R.mipmap.unemployment;
            promissName = "盗抢险";
        }
        Cursor cursor = db.query("Car",null,"perNum = "+ currentNum,null,null,null,null);
        if(cursor.moveToFirst()){
            do {
                String year = cursor.getString(cursor.getColumnIndex("carYear"));
                String price = cursor.getString(cursor.getColumnIndex("carPrice"));
                String carnum = cursor.getString(cursor.getColumnIndex("carNum"));
                CarData carData  = new CarData(year,price,carnum);
                carDataList.add(carData);
            }while(cursor.moveToNext());
        }
        cursor.close();
        Cursor cursor1 = db.query("Per",null,"num = "+currentNum,null,null,null,null);
        if(cursor1.moveToFirst()){
            carPromissPeople = cursor1.getString(cursor1.getColumnIndex("name"));
        }
        cursor1.close();
        carPromissShoper = "XXX保险";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buy_buy:
                if(car_year.getText().toString().length()<=0){
                    Toast.makeText(this,"请选择车辆",Toast.LENGTH_SHORT).show();
                }else if(promiss_price.getText().toString().length()<=0||promiss_year.getText().toString().length()<=0
                        ||promiss_money.getText().toString().length()<=0){
                    Toast.makeText(this,"请完善投保信息",Toast.LENGTH_SHORT).show();
                }else{
                    ContentValues values = new ContentValues();
                    values.put("number",currentNum);
                    values.put("imageId",imageId);
                    values.put("carYear",car_year.getText().toString());
                    values.put("carPrice",car_price.getText().toString());
                    values.put("carNum",car_num.getText().toString());
                    values.put("carPromissPeople",carPromissPeople);
                    values.put("carPromissShoper","XXX车辆保险公司");
                    values.put("carPromissPrice",promiss_price.getText().toString());
                    values.put("carPromissYear",promiss_year.getText().toString());
                    values.put("carPromissMoney",promiss_money.getText().toString());
                    values.put("promissPercent",promissPercent);
                    db.insert("Promiss",null,values);
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("温馨提醒：");
                    builder.setMessage("是否确认投保？");
                    builder.setPositiveButton("投保", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(PromissOrder.this);
                            builder1.setTitle("温馨提醒：");
                            builder1.setMessage("投保成功！");
                            builder1.setPositiveButton("好的", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            });
                            builder1.create().show();
                        }
                    });
                    builder.setNegativeButton("考虑一下",null);
                    builder.create().show();
                }
                break;
            case R.id.goodsBack:
                finish();
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(car_year.getText().toString().length()>0){
            double total = 0.0;
            double buyYear = 0.0;
            double subYear = 0.0;
            subYear = Calendar.getInstance().get(Calendar.YEAR) - Double.parseDouble(car_year.getText().toString());
            Log.e("subYear",subYear+"");
            double carPrice = 0.0;
            carPrice = Double.parseDouble(car_price.getText().toString());
            Log.e("carPrice",carPrice+"");
            if(promiss_year.getText().toString().length()>0){
                buyYear = Double.parseDouble(promiss_year.getText().toString());
            }else{
                buyYear = 0.0;
            }
            if(pos == 1){
                total = Math.round(665.0 * buyYear);
                carPromissPrice = total+"";
                promiss_price.setText(total+"");
            }else if(pos == 2){
                if(carPrice <= 5){
                    total = Math.round(626 * buyYear);
                    promiss_price.setText(total+"");
                    carPromissPrice = total+"";
                }else if(carPrice <= 10){
                    total = Math.round(903 * buyYear);
                    promiss_price.setText(total+"");
                    carPromissPrice = total+"";
                }else if(carPrice <= 15){
                    total = Math.round(1031 * buyYear);
                    promiss_price.setText(total+"");
                    carPromissPrice = total+"";
                }else if(carPrice <= 20){
                    total = Math.round(1120 * buyYear);
                    promiss_price.setText(total+"");
                    carPromissPrice = total+"";
                }else if(carPrice <= 30){
                    total = Math.round(1264 * buyYear);
                    promiss_price.setText(total+"");
                    carPromissPrice = total+"";
                }else if(carPrice <= 50){
                    total = Math.round(1516 * buyYear);
                    promiss_price.setText(total+"");
                    carPromissPrice = total+"";
                }else{
                    total = Math.round(1976 * buyYear);
                    promiss_price.setText(total+"");
                    carPromissPrice = total+"";
                }

            }else if(pos ==3){
                if(subYear <= 1){
                    total = Math.round((593 + carPrice * 0.0141*10000) * buyYear);
                    promiss_price.setText(total+"");
                    carPromissPrice = total+"";
                }else if(subYear <= 2){
                    total = Math.round((564 + carPrice * 0.0134*10000) * buyYear);
                    promiss_price.setText(total+"");
                    carPromissPrice = total+"";
                }else if(subYear <= 6){
                    total = Math.round((559 + carPrice * 0.0133*10000) * buyYear);
                    promiss_price.setText(total+"");
                    carPromissPrice = total+"";
                }else{
                    total = Math.round((576 + carPrice * 0.0137*10000) * buyYear);
                    promiss_price.setText(total+"");
                    carPromissPrice = total+"";
                }
            }else if(pos == 4){
                promiss_price.setText("具体金额请在订单中查看");
                carPromissPrice = "0.0";
            }else if(pos == 5){
                total = Math.round(carPrice * 0.00345 * buyYear*10000);
                promiss_price.setText(total+"");
                carPromissPrice = total+"";
            }else if(pos == 6){
                total = Math.round((120 + carPrice*0.0041*10000)*buyYear);
                promiss_price.setText(total+"");
                carPromissPrice = total+"";
            }
            carPromissMoney = promiss_money.getText().toString();
        }else{
            Toast.makeText(this,"车辆未选择",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
