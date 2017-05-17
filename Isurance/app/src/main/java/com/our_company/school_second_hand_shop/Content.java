package com.our_company.school_second_hand_shop;

import android.content.Context;
import android.os.Handler;

import com.our_company.school_second_hand_shop.DataClass.GoodsInfoDescrib;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lance on 2017/3/23.
 */

public class Content {
    public static final int LOGININ_SUCCESS = 1;
    public static final int MODIFY_SUCCESS = 2;
    public static final int ADD_CARINFO = 3;
    public static final int CHANGE_CAR = 4;
    public static final int LOGIN_OUT = 5;

    public  static int WIDTH = 0;
    public static List<GoodsInfoDescrib> list = new ArrayList<>();
    public static Context mainContext;
    public static  Context buyContext;
    public static String issue_classify = "";
    public static String issue_school = "";

    public static String currentNum;
    public static String currentPass;
    public static int isLoginIn = 0;

    public static Handler handler;
    public static int carPosition = 0;
}
