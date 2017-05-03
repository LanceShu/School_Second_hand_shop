package com.our_company.school_second_hand_shop.MyAdapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.our_company.school_second_hand_shop.DataClass.AdvertisementData;
import com.our_company.school_second_hand_shop.R;

import java.util.List;

/**
 * Created by LZL on 2017/4/29.
 */

public class ADListAdapter extends RecyclerView.Adapter<ADListAdapter.AdViewHoder> {
    List<AdvertisementData> dataList;
    public ADListAdapter(List<AdvertisementData> lIst)
    {
        dataList = lIst;
    }

    @Override
    public void onBindViewHolder(AdViewHoder adViewHoder, int i) {
        adViewHoder.imageView.setImageResource(dataList.get(i).getImageId());
        adViewHoder.cardView.setCardElevation(10);
    }

    @Override
    public AdViewHoder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ad_list_item,viewGroup,false);
        AdViewHoder viewHoder = new AdViewHoder(view);
        return viewHoder;
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class AdViewHoder extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        CardView cardView;
        public AdViewHoder(View view)
        {
            super(view);
            imageView = (ImageView)view.findViewById(R.id.ad_content_image);
            cardView = (CardView)view.findViewById(R.id.ad_card);
        }
    }
}
