package com.our_company.school_second_hand_shop.Fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.our_company.school_second_hand_shop.DataBase.MyDatabaseHelper;
import com.our_company.school_second_hand_shop.DataClass.PromissData;
import com.our_company.school_second_hand_shop.MyAdapter.PromissAdapter;
import com.our_company.school_second_hand_shop.R;

import java.util.ArrayList;
import java.util.List;
import static com.our_company.school_second_hand_shop.Content.*;

/**
 * Created by LZL on 2017/4/29.
 */

public class FragmentShop extends Fragment {
    private View root;
    private List<PromissData> dataList;
    private RecyclerView recyclerView;
    private TextView noLogin;
    private PromissAdapter promissAdapter;
    private TextView totalmoney;

    private MyDatabaseHelper myDatabaseHelper;
    private SQLiteDatabase db;

    private int imageId;        //险种的图片;
    private String carYear;     //购车的年份;
    private String carPrice;    //购车的价格；
    private String carNum;      //车牌号码;
    private String promissShoper;//保险人;
    private String promissPeople;//被保险人;
    private String promissMoney; //保额;
    private String promissYear;  //保期;
    private String promissPrice; //保金;

    private double total = 0.0; //总金额;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDatabaseHelper = new MyDatabaseHelper(getContext(),"PromissDB.db",null,2);
        db = myDatabaseHelper.getWritableDatabase();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_shopped,container,false);
        dataList = new ArrayList<>();
        getData();
        initView();
        return root;
    }

    private void getData() {
        Cursor cursor = db.query("Promiss",null,"number = "+currentNum,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                imageId = cursor.getInt(cursor.getColumnIndex("imageId"));
                carYear = cursor.getString(cursor.getColumnIndex("carYear"));
                carPrice = cursor.getString(cursor.getColumnIndex("carPrice"));
                carNum = cursor.getString(cursor.getColumnIndex("carNum"));
                promissPeople = cursor.getString(cursor.getColumnIndex("carPromissPeople"));
                promissShoper = cursor.getString(cursor.getColumnIndex("carPromissShoper"));
                promissMoney = cursor.getString(cursor.getColumnIndex("carPromissMoney"));
                promissYear = cursor.getString(cursor.getColumnIndex("carPromissYear"));
                if(imageId == R.mipmap.life){
                    promissPrice = setLifePrice(carNum); //计算不计免赔险的保险费;
                    total += Double.parseDouble(promissPrice);
                }else{
                    promissPrice = cursor.getString(cursor.getColumnIndex("carPromissPrice"));
                    total += Double.parseDouble(promissPrice);
                }
                String promissPercent = cursor.getColumnName(cursor.getColumnIndex("promissPercent"));
                PromissData promissData = new PromissData(imageId,carYear,carPrice,carNum,promissPeople,promissShoper,promissMoney,promissYear,promissPrice,promissPercent);
                dataList.add(promissData);
            }while(cursor.moveToNext());
        }
        cursor.close();
    }

    private String setLifePrice(String carNum) {
        double price = 0.0;
        Cursor cursor = db.query("Promiss",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            if(cursor.getString(cursor.getColumnIndex("carNum")).equals(carNum)){
                do {
                    if(cursor.getInt(cursor.getColumnIndex("imageId")) == R.mipmap.medical||cursor.getInt(cursor.getColumnIndex("imageId"))== R.mipmap.property){
                        price = price + Double.parseDouble(cursor.getString(cursor.getColumnIndex("carPromissPrice")));
                    }
                }while(cursor.moveToNext());
            }
        }
        cursor.close();
        price = price * 0.2;
        return  Math.round(price)+"";
    }

    public void initView()
    {
        recyclerView = (RecyclerView)root.findViewById(R.id.discovery_ad_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        promissAdapter = new PromissAdapter(dataList);
        recyclerView.setAdapter(promissAdapter);
        totalmoney = (TextView) root.findViewById(R.id.totalmoney);
        totalmoney.setText(total+"");
        noLogin = (TextView) root.findViewById(R.id.no_loginin);

        if(isLoginIn == 1){
            recyclerView.setVisibility(View.VISIBLE);
            noLogin.setVisibility(View.GONE);
        }else{
            recyclerView.setVisibility(View.GONE);
            noLogin.setVisibility(View.VISIBLE);
        }
    }
}
