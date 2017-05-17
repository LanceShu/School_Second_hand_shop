package com.our_company.school_second_hand_shop.DataClass;

import android.graphics.Bitmap;

/**
 * Created by LZL on 2017/4/29.
 */

public class PromissData {
    public int imageId;             //图片id
    public String carYear;          //购车年份
    public String carPrice;         //购车价格
    public String carNum;           //车牌号
    public String carPromissPeople; //被保险人
    public String carPromissShoper; //保险人
    public String carPromissPrice;  //保额
    public String carPromissYear;   //保险期限
    public String carPromissMoney;  //保金
    public String promissPercent;   //赔率

    public PromissData(int imageId,String carYear,String carPrice,String carNum,String carPromissPeople,
                       String carPromissShoper,String carPromissPrice,String carPromissYear,String carPromissMoney,String promissPercent){
        this.imageId = imageId;
        this.carYear = carYear;
        this.carPrice = carPrice;
        this.carNum = carNum;
        this.carPromissPeople = carPromissPeople;
        this.carPromissShoper = carPromissShoper;
        this.carPromissPrice = carPromissPrice;
        this.carPromissYear = carPromissYear;
        this.carPromissMoney  = carPromissMoney;
        this.promissPercent = promissPercent;
    }
}
