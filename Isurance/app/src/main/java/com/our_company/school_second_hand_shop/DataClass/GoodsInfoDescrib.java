package com.our_company.school_second_hand_shop.DataClass;

import android.graphics.Bitmap;

/**
 * Created by Lance on 2017/4/17.
 */

public class GoodsInfoDescrib {

    public String goodsPrice;
    public String bitmapUrl;
    public String goodsInfo;
    public String goodsTime;
    public String goodsContent;

    public GoodsInfoDescrib(String Price,String bitmap,String goodsInfo,String content,String goodsTime){
        goodsPrice = Price;
        bitmapUrl = bitmap;
        this.goodsInfo = goodsInfo;
        goodsContent = content;
        this.goodsTime = goodsTime;
    }


}
