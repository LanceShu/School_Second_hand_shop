package com.our_company.school_second_hand_shop.MyView;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.our_company.school_second_hand_shop.Content;
import com.our_company.school_second_hand_shop.R;

import java.util.List;

/**
 * Created by Lance on 2017/3/9.
 */
public class ImageBarnnerFrameLayout extends FrameLayout implements ImageBarnerViewGroup.ImageBarnnerViewGroupListner,ImageBarnerViewGroup.ImageBarnnerLister{

    private ImageBarnerViewGroup imageBarnerViewGroup;
    private LinearLayout linearLayout;

    private FrameLayoutListner layoutListner;

    public ImageBarnnerFrameLayout(Context context) {
        super(context);
        initImageBarnnerViewGroup();
        initDotLinearLayout();
    }

    public ImageBarnnerFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initImageBarnnerViewGroup();
        initDotLinearLayout();
    }

    public ImageBarnnerFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initImageBarnnerViewGroup();
        initDotLinearLayout();
    }

    //初始化顶部原点的buju;
    private void initDotLinearLayout() {
        linearLayout = new LinearLayout(getContext());
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, 40);
        linearLayout.setLayoutParams(lp);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setBackgroundColor(0x00000000);
        addView(linearLayout);

        FrameLayout.LayoutParams layoutParams = (LayoutParams) linearLayout.getLayoutParams();
        layoutParams.gravity = Gravity.BOTTOM;

        linearLayout.setLayoutParams(layoutParams);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            linearLayout.setAlpha(0.5f);
        }else{
            linearLayout.getBackground().setAlpha(100);
        }
    }


    //初始化自定义的图片轮播功能的核心类;
    private void initImageBarnnerViewGroup() {
        imageBarnerViewGroup = new ImageBarnerViewGroup(getContext());
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        imageBarnerViewGroup.setLayoutParams(lp);
        imageBarnerViewGroup.setBarnnerViewGroupListner(this);
        imageBarnerViewGroup.setLister(this);
        addView(imageBarnerViewGroup);
    }

    public void addBitmaps(List<Bitmap> list) {
        for(int i = 0;i<list.size();i++){
            Bitmap bitmap = list.get(i);
            addBitmapToImnageBarnnerViewGroup(bitmap);
            addDotToLinearLayout();
        }
    }

    private void addDotToLinearLayout() {
        ImageView iv = new ImageView(getContext());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(5,5,5,5);
        iv.setLayoutParams(lp);
        iv.setImageResource(R.drawable.dot_normal);
        linearLayout.addView(iv);
    }

    private void addBitmapToImnageBarnnerViewGroup(Bitmap bitmap) {
        ImageView iv = new ImageView(getContext());
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        iv.setLayoutParams(new ViewGroup.LayoutParams(Content.WIDTH, ViewGroup.LayoutParams.MATCH_PARENT));
        iv.setImageBitmap(bitmap);
        imageBarnerViewGroup.addView(iv);
    }

    @Override
    public void selectImage(int index) {
        int count = linearLayout.getChildCount();
        for(int i =0;i<count;i++){
            ImageView iv = (ImageView) linearLayout.getChildAt(i);
            if(i == index){
                iv.setImageResource(R.drawable.dot_select);
            }else {
                iv.setImageResource(R.drawable.dot_normal);
            }
        }
    }

    @Override
    public void clickImageIndex(int pos) {
        layoutListner.clickImageIndex(pos);
    }

    public FrameLayoutListner getLayoutListner() {
        return layoutListner;
    }

    public void setLayoutListner(FrameLayoutListner layoutListner) {
        this.layoutListner = layoutListner;
    }

    public  interface FrameLayoutListner{
        void clickImageIndex(int pos);
    }
}
