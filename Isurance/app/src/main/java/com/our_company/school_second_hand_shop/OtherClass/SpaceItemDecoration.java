package com.our_company.school_second_hand_shop.OtherClass;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;

/**
 * Created by LZL on 2017/3/29.
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    /*
    * 该类是用来给recyclerVIew的每一个item之间添加间距的
    * */

    private int space;      //间距大小 单位 px

    public SpaceItemDecoration(int space)
    {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        outRect.bottom = space;     //bottom 下方间距
    }
}
