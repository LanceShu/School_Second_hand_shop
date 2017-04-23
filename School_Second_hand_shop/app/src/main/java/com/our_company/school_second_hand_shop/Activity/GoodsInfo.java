package com.our_company.school_second_hand_shop.Activity;

import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.our_company.school_second_hand_shop.R;

import static com.our_company.school_second_hand_shop.Content.*;

/**
 * Created by Lance on 2017/4/18.
 */

public class GoodsInfo extends AppCompatActivity implements View.OnClickListener{

    private Toolbar toolbar;
    private ImageView back;
    private ImageView goodsImage;
    private TextView goodsInfo;
    private TextView goodsContent;
    private TextView goodsPrice;
    private TextView goodsCommuni;
    private TextView goodsBuy;

    private String imageurl;
    private String goodstitle;
    private String goodscontent;
    private String goodsprice;

    private int position;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.goods);
        position = getIntent().getIntExtra("position",-1);

        initWight(position);
    }

    private void initWight(int position) {
        toolbar = (Toolbar) findViewById(R.id.toolbars);
        back = (ImageView) findViewById(R.id.goodsBack);
        goodsImage = (ImageView) findViewById(R.id.goodsImage);
        goodsInfo = (TextView) findViewById(R.id.goodsTitle);
        goodsContent = (TextView) findViewById(R.id.goodsContent);
        goodsPrice = (TextView) findViewById(R.id.goodsPrice);
        goodsCommuni = (TextView) findViewById(R.id.goodsCommunicate);
        goodsBuy = (TextView) findViewById(R.id.goodsBuy);

        goodsInfo.setText(list.get(position).goodsInfo);
        goodsContent.setText(list.get(position).goodsContent);
        goodsPrice.setText(list.get(position).goodsPrice);

        back.setOnClickListener(this);
        goodsCommuni.setOnClickListener(this);
        goodsBuy.setOnClickListener(this);
        toolbar.inflateMenu(R.menu.goods_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.goods_tousu:
                        Toast.makeText(GoodsInfo.this,"举报",Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.goodsBack:
                finish();
                break;
            case R.id.goodsCommunicate:
                Dialog builder = new Dialog(this);
                View view = View.inflate(this,R.layout.goods_tel,null);
                builder.setContentView(view);
                builder.setCancelable(true);
                builder.setTitle("联系卖家：");
                builder.create();
                builder.show();
                break;
            case R.id.goodsBuy:
                Intent intent = new Intent(this,GoodsOrder.class);
                intent.putExtra("imageUrl",list.get(position).bitmapUrl);
                intent.putExtra("goodsInfo",list.get(position).goodsInfo);
                intent.putExtra("goodsPrice",list.get(position).goodsPrice);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                break;
        }
    }
}
