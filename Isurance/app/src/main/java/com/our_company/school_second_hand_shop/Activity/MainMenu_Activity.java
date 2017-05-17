package com.our_company.school_second_hand_shop.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.our_company.school_second_hand_shop.Content;
import com.our_company.school_second_hand_shop.DataBase.MyDatabaseHelper;
import com.our_company.school_second_hand_shop.Fragment.FragmentHome;
import com.our_company.school_second_hand_shop.Fragment.FragmentMe;
import com.our_company.school_second_hand_shop.Fragment.FragmentShop;
import com.our_company.school_second_hand_shop.R;


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
    MyDatabaseHelper myDatabaseHelper;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        myDatabaseHelper = new MyDatabaseHelper(this,"PromissDB.db",null,2);
        myDatabaseHelper.getWritableDatabase();
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
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.home,"首页")).addItem(new BottomNavigationItem(R.mipmap.shop,"订单"))
                .addItem(new BottomNavigationItem(R.mipmap.me,"我的")).setFirstSelectedPosition(0).setMode(BottomNavigationBar.MODE_FIXED);
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
                replaceFragment(new FragmentShop());
                break;
            case 2:
                replaceFragment(new FragmentMe());
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
