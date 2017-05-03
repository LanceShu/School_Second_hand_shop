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

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.our_company.school_second_hand_shop.Content;
import com.our_company.school_second_hand_shop.Fragment.FragmentDiscovery;
import com.our_company.school_second_hand_shop.Fragment.FragmentHome;
import com.our_company.school_second_hand_shop.MyView.ImageBarnnerFrameLayout;
import com.our_company.school_second_hand_shop.R;

import java.util.ArrayList;
import java.util.List;
import static com.our_company.school_second_hand_shop.Content.*;

/**
 * Created by Lance on 2017/3/22.
 */

public class MainMenu_Activity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener{

    private FrameLayout frameLayout;
    private RelativeLayout home_layout;
    private RelativeLayout find_layout;
    private RelativeLayout me_layout;
    private BottomNavigationBar bottomNavigationBar;

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
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.main_menu_bottom);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.home,"首页")).addItem(new BottomNavigationItem(R.mipmap.glasses,"发现"))
                .addItem(new BottomNavigationItem(R.mipmap.me,"我")).setFirstSelectedPosition(0).setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.initialise();
        bottomNavigationBar.setPadding(0,5,0,0);
        bottomNavigationBar.setTabSelectedListener(this);
        replaceFragment(new FragmentHome());
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_menu_container,fragment);
        transaction.commit();
    }

    @Override
    public void onTabSelected(int position) {

        switch (position){
            case 0:
                replaceFragment(new FragmentHome());
                break;
            case 1:
                replaceFragment(new FragmentDiscovery());
                break;
            case 2:
                break;
            default:
                break;
        }
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
