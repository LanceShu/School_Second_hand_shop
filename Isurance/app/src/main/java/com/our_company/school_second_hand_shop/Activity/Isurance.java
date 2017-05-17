package com.our_company.school_second_hand_shop.Activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.util.Log;
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

public class Isurance extends AppCompatActivity implements View.OnClickListener{

    private ImageView back;
    private ImageView goodsImage;
    private TextView goodsWeb;
    private TextView goodsOrder;
    private TextView issureContent;

    private int position;
    private String content;
    private String uri;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        Fade fade = new Fade();
        fade.setDuration(500);
        getWindow().setEnterTransition(fade);
        setContentView(R.layout.isurance);
        position = getIntent().getIntExtra("image",-1);
        Log.e("image",position+"");
        content = getIntent().getStringExtra("content");
        Log.e("content",content);
        uri = getIntent().getStringExtra("uri");
        Log.e("uri",uri);
        initWight();

    }

    private void initWight() {
        back = (ImageView) findViewById(R.id.goodsBack);
        goodsImage = (ImageView) findViewById(R.id.goodsImage);
        goodsWeb = (TextView) findViewById(R.id.goodsPrice);
        goodsOrder = (TextView) findViewById(R.id.goodsCommunicate);
        issureContent = (TextView) findViewById(R.id.goodsContent);

        goodsWeb.setOnClickListener(this);
        back.setOnClickListener(this);
        goodsOrder.setOnClickListener(this);

        switch (position){
            case 1:
                goodsImage.setImageResource(R.mipmap.endowment);
                issureContent.setText(content);
                break;
            case 2:
                goodsImage.setImageResource(R.mipmap.medical);
                issureContent.setText(content);
                break;
            case 3:
                goodsImage.setImageResource(R.mipmap.property);
                issureContent.setText(content);
                break;
            case 4:
                goodsImage.setImageResource(R.mipmap.life);
                issureContent.setText(content);
                break;
            case 5:
                goodsImage.setImageResource(R.mipmap.occupational);
                issureContent.setText(content);
                break;
            case 6:
                goodsImage.setImageResource(R.mipmap.unemployment);
                issureContent.setText(content);
                break;
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.goodsBack:
                finish();
                break;
            case R.id.goodsCommunicate:
                if(isLoginIn == 0){
                    Toast.makeText(this,"尚未登陆",Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent1 = new Intent(Isurance.this,PromissOrder.class);
                    intent1.putExtra("position",position);
                    startActivity(intent1);
                }
                break;
            case R.id.goodsPrice:
                Intent intent = new Intent(this,IsuranceInfo.class);
                intent.putExtra("uri",uri);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                break;
        }
    }
}
