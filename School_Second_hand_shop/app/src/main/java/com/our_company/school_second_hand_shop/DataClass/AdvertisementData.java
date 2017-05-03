package com.our_company.school_second_hand_shop.DataClass;

import android.graphics.Bitmap;

/**
 * Created by LZL on 2017/4/29.
 */

public class AdvertisementData {
    private int imageId;
    private Bitmap images;

    public int getImageId() {
        return imageId;
    }

    public Bitmap getImages() {
        return images;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public void setImages(Bitmap images) {
        this.images = images;
    }

    public AdvertisementData(int imageid)
    {
        imageId = imageid;
    }
    public AdvertisementData(Bitmap bitmap)
    {
        images = bitmap;
    }
}
