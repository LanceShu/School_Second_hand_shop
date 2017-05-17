package com.our_company.school_second_hand_shop.MyAdapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.our_company.school_second_hand_shop.DataClass.PromissData;
import com.our_company.school_second_hand_shop.R;

import java.util.List;

/**
 * Created by LZL on 2017/4/29.
 */

public class PromissAdapter extends RecyclerView.Adapter<PromissAdapter.AdViewHoder> {
    List<PromissData> dataList;

    public PromissAdapter(List<PromissData> List)
    {
        dataList = List;
    }

    @Override
    public void onBindViewHolder(AdViewHoder adViewHoder, int i) {
        adViewHoder.cardView.setCardElevation(6);
        adViewHoder.imageView.setImageResource(dataList.get(i).imageId);
//        adViewHoder.carYear.setText(dataList.get(i).carYear);
//        adViewHoder.carPrice.setText(dataList.get(i).carPrice);
        adViewHoder.carNum.setText(dataList.get(i).carNum);
//        adViewHoder.carPromissPeople.setText(dataList.get(i).carPromissPeople);
//        adViewHoder.carPromissShoper.setText(dataList.get(i).carPromissShoper);
        adViewHoder.carPromissPrice.setText(dataList.get(i).carPromissMoney);
        adViewHoder.carPromissYear.setText(dataList.get(i).carPromissYear);
        adViewHoder.carPromissMoney.setText(dataList.get(i).carPromissPrice);
    }

    @Override
    public AdViewHoder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.promiss_list_item,viewGroup,false);
        AdViewHoder viewHoder = new AdViewHoder(view);
        return viewHoder;
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class AdViewHoder extends RecyclerView.ViewHolder
    {
        CardView cardView;
        ImageView imageView;
//        TextView carYear;
//        TextView carPrice;
        TextView carNum;
//        TextView carPromissPeople;
//        TextView carPromissShoper;
        TextView carPromissPrice;
        TextView carPromissYear;
        TextView carPromissMoney;

        public AdViewHoder(View view)
        {
            super(view);
            cardView = (CardView)view.findViewById(R.id.ad_card);
            imageView = (ImageView)view.findViewById(R.id.ad_image);
//            carYear = (TextView) view.findViewById(R.id.caryear);
//            carPrice = (TextView) view.findViewById(R.id.carprice);
            carNum = (TextView) view.findViewById(R.id.carnum);
//            carPromissPeople = (TextView) view.findViewById(R.id.carpromisspeople);
//            carPromissShoper = (TextView) view.findViewById(R.id.carpromissshoper);
            carPromissPrice = (TextView) view.findViewById(R.id.carpromissprice);
            carPromissYear = (TextView) view.findViewById(R.id.carpromissyear);
            carPromissMoney = (TextView) view.findViewById(R.id.carpromissmoney);
        }
    }
}
