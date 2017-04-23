package com.our_company.school_second_hand_shop.Activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.our_company.school_second_hand_shop.MyAdapter.GoodsAdapter;
import com.our_company.school_second_hand_shop.R;
import static com.our_company.school_second_hand_shop.Content.*;

/**
 * Created by Lance on 2017/4/21.
 */

public class GoodsFilter extends AppCompatActivity implements View.OnClickListener{

    private ImageView back;
    private EditText edit;
    private TextView select;
    private RecyclerView goods;

    private String classify;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        Slide fade = new Slide();
        fade.setDuration(300);
        getWindow().setEnterTransition(fade);
        setContentView(R.layout.goods_filter);
        classify = getIntent().getStringExtra("classify");
        initWight();
    }

    private void initWight() {
        back = (ImageView) findViewById(R.id.filter_back);
        edit = (EditText) findViewById(R.id.filter_edit);
        select = (TextView) findViewById(R.id.filter_select);
        goods = (RecyclerView) findViewById(R.id.filter_recyclerView);

        back.setOnClickListener(this);
        select.setOnClickListener(this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        goods.setLayoutManager(gridLayoutManager);

        if(classify.equals("hobby")){
            GoodsAdapter goodsAdapter = new GoodsAdapter(this,list);
            goods.setAdapter(goodsAdapter);
        }else if(classify.equals("computer")){
            GoodsAdapter goodsAdapter = new GoodsAdapter(this,list);
            goods.setAdapter(goodsAdapter);
        }else if(classify.equals("books")){
            GoodsAdapter goodsAdapter = new GoodsAdapter(this,list);
            goods.setAdapter(goodsAdapter);
        }else if(classify.equals("mouses")){
            GoodsAdapter goodsAdapter = new GoodsAdapter(this,list);
            goods.setAdapter(goodsAdapter);
        }else if(classify.equals("gifts")){
            GoodsAdapter goodsAdapter = new GoodsAdapter(this,list);
            goods.setAdapter(goodsAdapter);
        }else if(classify.equals("basketball")){
            GoodsAdapter goodsAdapter = new GoodsAdapter(this,list);
            goods.setAdapter(goodsAdapter);
        }else if(classify.equals("clothes")){
            GoodsAdapter goodsAdapter = new GoodsAdapter(this,list);
            goods.setAdapter(goodsAdapter);
        }else if(classify.equals("phones")){
            GoodsAdapter goodsAdapter = new GoodsAdapter(this,list);
            goods.setAdapter(goodsAdapter);
        }else if(classify.equals("cosmeticss")){
            GoodsAdapter goodsAdapter = new GoodsAdapter(this,list);
            goods.setAdapter(goodsAdapter);
        }else if(classify.equals("others")){
            GoodsAdapter goodsAdapter = new GoodsAdapter(this,list);
            goods.setAdapter(goodsAdapter);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.filter_back:
                finish();
                break;
            case R.id.filter_select:
                String goodsFilter = "";
                goodsFilter = edit.getText().toString();
                Snackbar.make(goods,"已筛选"+"--"+goodsFilter,Snackbar.LENGTH_SHORT).show();
                edit.setText("");
                break;
        }
    }
}
