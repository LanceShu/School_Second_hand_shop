package com.our_company.school_second_hand_shop.MyAdapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.our_company.school_second_hand_shop.Activity.GoodsInfo;
import com.our_company.school_second_hand_shop.DataClass.GoodsInfoDescrib;
import com.our_company.school_second_hand_shop.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lance on 2017/4/17.
 */

public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.ViewHolder> {

    List<GoodsInfoDescrib> list = new ArrayList<>();
    private String imageUrl;
    private String goodsInfo;
    private String goodsContent;
    private String goodsPrice;
    private Context context;
    private int pos;

    public GoodsAdapter(Context context,List<GoodsInfoDescrib> list){
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_goods_info,parent,false);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(parent.getContext(), GoodsInfo.class);
                pos = viewHolder.getPosition();
                Log.e("position",pos+"");
                intent.putExtra("position",pos);
                context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((Activity) context,viewHolder.imageView,"goods").toBundle());
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GoodsInfoDescrib goodsInfoDescrib = list.get(position);
//        Glide.with(new FragmentHome()).load(goodsInfoDescrib.bitmapUrl).override(100,100).into(holder.imageView);
        holder.imageView.setImageResource(R.mipmap.computer);
        holder.contentView.setText(goodsInfoDescrib.goodsInfo);
        holder.timeView.setText(goodsInfoDescrib.goodsTime);
        Log.e("asdasda",position+"");
//        pos = position;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView contentView;
        TextView timeView;
        View view;
        LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            linearLayout = (LinearLayout) itemView.findViewById(R.id.goods_info_item);
            imageView = (ImageView) itemView.findViewById(R.id.goods_image);
            contentView = (TextView) itemView.findViewById(R.id.goods_content);
            timeView = (TextView) itemView.findViewById(R.id.goods_time);
        }
    }
}
