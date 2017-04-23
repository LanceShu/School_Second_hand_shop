package com.our_company.school_second_hand_shop.Activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.our_company.school_second_hand_shop.R;
import static com.our_company.school_second_hand_shop.Content.*;

/**
 * Created by Lance on 2017/4/19.
 */

public class GoodsOrder extends AppCompatActivity implements View.OnClickListener{

    private ImageView goods_image;
    private TextView goods_info;
    private TextView goods_price;
    private Button goods_buy;
    private TextView goods_total;
    private TextView goods_yunfei;

    private String imageUrl;
    private String goodsInfo;
    private String goodsPrice;
    private String address;
    private String yunfei;
    private String total;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        Fade fade = new Fade();
        fade.setDuration(500);
        getWindow().setEnterTransition(fade);
        setContentView(R.layout.goods_order);
        getData();          //得到上个activity传递过来的数据;
        initWidght();       //实例化控件;
    }

    private void initWidght() {
        goods_image = (ImageView) findViewById(R.id.buy_image);
        goods_info = (TextView) findViewById(R.id.buy_info);
        goods_price = (TextView) findViewById(R.id.buy_price);
        goods_buy = (Button) findViewById(R.id.buy_buy);
        goods_total = (TextView) findViewById(R.id.buy_total);
        goods_yunfei = (TextView) findViewById(R.id.buy_yunfei);

        goods_buy.setOnClickListener(this);

//        goods_image.setImageBitmap();
        goods_info.setText(goodsInfo);
        goods_price.setText(goodsPrice);

        yunfei = "0.0";
        goods_yunfei.setText(yunfei);
        double total = Double.parseDouble(yunfei)+Double.parseDouble(goodsPrice);
        goods_total.setText(total+"");
    }

    private void getData() {
//        intent.putExtra("imageUrl",list.get(position).bitmapUrl);
//        intent.putExtra("goodsInfo",list.get(position).goodsInfo);
//        intent.putExtra("goodsPrice",list.get(position).goodsPrice);
        Intent intent = getIntent();
        imageUrl = intent.getStringExtra("imageUrl");
        goodsInfo = intent.getStringExtra("goodsInfo");
        goodsPrice = intent.getStringExtra("goodsPrice");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buy_buy:
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("温馨提示：");
                builder.setMessage("是否确认下单？");
                builder.setNegativeButton("取消",null);
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Snackbar.make(goods_buy,"购买成功!",Snackbar.LENGTH_SHORT).show();
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(GoodsOrder.this);
                        builder1.setTitle("温馨提示：");
                        builder1.setMessage("购买成功！");
                        builder1.setPositiveButton("好的", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        }).create().show();
                    }
                }).create().show();

                break;
        }
    }
}
