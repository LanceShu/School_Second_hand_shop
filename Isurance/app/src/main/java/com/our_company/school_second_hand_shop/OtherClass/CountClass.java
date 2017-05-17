package com.our_company.school_second_hand_shop.OtherClass;

import com.our_company.school_second_hand_shop.Interface.CountListener;

/**
 * Created by Lance on 2017/3/21.
 */

public class CountClass {

    public static void startCount(final CountListener listener)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 60;i>0;i--)
                {
                    listener.doCount(i);
                    try
                    {
                        Thread.sleep(1000);
                    }catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
                listener.overCount();
            }
        }).start();
    }
}