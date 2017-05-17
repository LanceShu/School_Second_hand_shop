package com.our_company.school_second_hand_shop.MyAdapter;

import android.content.Context;
import android.os.Message;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.our_company.school_second_hand_shop.DataClass.CarData;
import com.our_company.school_second_hand_shop.R;

import java.util.List;
import static com.our_company.school_second_hand_shop.Content.*;

/**
 * Created by Lance on 2017/5/10.
 */

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder> {

    List<CarData> carDataList;
    Context context;

    public CarAdapter(Context context,List<CarData> carDatas){
        carDataList = carDatas;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_info_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.cardView.setCardElevation(5);
        holder.carYear.setText(carDataList.get(position).year);
        holder.carPrice.setText(carDataList.get(position).price);
        holder.carNum.setText(carDataList.get(position).num);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carPosition = position;
                Message message = new Message();
                message.what = CHANGE_CAR;
                handler.sendMessage(message);

            }
        });
    }

    @Override
    public int getItemCount() {
        return carDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        TextView carYear;
        TextView carPrice;
        TextView carNum;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.car_card);
            carYear = (TextView) view.findViewById(R.id.car_year);
            carPrice = (TextView) view.findViewById(R.id.car_price);
            carNum = (TextView) view.findViewById(R.id.car_num);
        }
    }
}
