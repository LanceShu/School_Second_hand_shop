package com.our_company.school_second_hand_shop.Activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.our_company.school_second_hand_shop.Content;
import com.our_company.school_second_hand_shop.Fragment.FragmentHome;
import com.our_company.school_second_hand_shop.MyView.ImageBarnnerFrameLayout;
import com.our_company.school_second_hand_shop.R;

import java.util.ArrayList;
import java.util.List;
import static com.our_company.school_second_hand_shop.Content.*;

/**
 * Created by Lance on 2017/3/22.
 */

public class MainMenu_Activity extends AppCompatActivity implements View.OnClickListener{

    private FrameLayout frameLayout;
    private RelativeLayout home_layout;
    private RelativeLayout find_layout;
    private RelativeLayout me_layout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        mainContext = this;
        initCW();

        //计算手机当前的宽度;
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        Content.WIDTH = dm.widthPixels;

    }

    private void initCW() {
        frameLayout = (FrameLayout) findViewById(R.id.main_menu_container);
        home_layout = (RelativeLayout) findViewById(R.id.main_menu_home_layout);
        find_layout = (RelativeLayout) findViewById(R.id.main_menu_discovery_layout);
        me_layout = (RelativeLayout) findViewById(R.id.main_menu_me_layout);

        home_layout.setOnClickListener(this);
        home_layout.setOnClickListener(this);
        me_layout.setOnClickListener(this);

        replaceFragment(new FragmentHome());
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_menu_home_layout:
                replaceFragment(new FragmentHome());
                break;
            case R.id.main_menu_discovery_layout:
                break;
            case R.id.main_menu_me_layout:
                break;

        }
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_menu_container,fragment);
        transaction.commit();
    }
}
