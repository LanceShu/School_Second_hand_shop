package com.our_company.school_second_hand_shop.Activity;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.our_company.school_second_hand_shop.R;

/**
 * Created by Lance on 2017/4/28.
 */

public class SelectPictruePopupWindow extends PopupWindow {

    private TextView takePhoto;
    private TextView pickPhoto;
    private TextView cancelPhoto;
    private View mView;

    public SelectPictruePopupWindow(Context context, View.OnClickListener itemsOnClick){
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = inflater.inflate(R.layout.choose_image,null);
        this.setContentView(mView);

        takePhoto = (TextView) mView.findViewById(R.id.picture_take);
        pickPhoto = (TextView) mView.findViewById(R.id.picture_pick);
        cancelPhoto = (TextView) mView.findViewById(R.id.picture_cancel);

        cancelPhoto.setOnClickListener(itemsOnClick);
        takePhoto.setOnClickListener(itemsOnClick);
        pickPhoto.setOnClickListener(itemsOnClick);

        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);

        ColorDrawable dw = new ColorDrawable(0x80000000);
        this.setBackgroundDrawable(dw);
        this.setAnimationStyle(R.style.PopupAnimation);
    }
}
