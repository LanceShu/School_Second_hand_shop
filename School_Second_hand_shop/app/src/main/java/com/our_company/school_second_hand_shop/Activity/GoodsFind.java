package com.our_company.school_second_hand_shop.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.Explode;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.our_company.school_second_hand_shop.R;

/**
 * Created by Lance on 2017/4/21.
 */

public class GoodsFind extends AppCompatActivity implements TextWatcher,View.OnClickListener{

    private EditText find_edit;
    private TextView find_select;
    private int isFind = 0;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        Explode explode = new Explode();
        explode.setDuration(300);
        getWindow().setEnterTransition(explode);
        setContentView(R.layout.goods_find);
        initWight();
    }

    private void initWight() {
        find_edit = (EditText) findViewById(R.id.find_edit);
        find_select = (TextView) findViewById(R.id.find_select);
        find_edit.addTextChangedListener(this);
        find_select.setOnClickListener(this);
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(s.length()>0){
            find_select.setText("搜索");
            isFind = 1;
        }else{
            find_select.setText("取消");
            isFind = 0;
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.find_select:
                if(isFind == 0){
                    finish();
                }else{
                    Toast.makeText(this,"搜索",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                }
                break;
        }
    }
}
