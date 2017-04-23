package com.our_company.school_second_hand_shop.Fragment;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.our_company.school_second_hand_shop.Activity.GoodsFilter;
import com.our_company.school_second_hand_shop.Activity.GoodsFind;
import com.our_company.school_second_hand_shop.Activity.MainMenu_Activity;
import com.our_company.school_second_hand_shop.Content;
import com.our_company.school_second_hand_shop.DataClass.GoodsInfoDescrib;
import com.our_company.school_second_hand_shop.MyAdapter.GoodsAdapter;
import com.our_company.school_second_hand_shop.MyView.ImageBarnnerFrameLayout;
import com.our_company.school_second_hand_shop.R;

import java.util.ArrayList;
import java.util.List;
import static com.our_company.school_second_hand_shop.Content.*;

/**
 * Created by Lance on 2017/4/17.
 */

public class FragmentHome extends Fragment implements ImageBarnnerFrameLayout.FrameLayoutListner,View.OnClickListener{

    private ImageBarnnerFrameLayout mGroup;
    private RecyclerView recyclerView;
    private List<GoodsInfoDescrib> goodsInfoDescribList = new ArrayList<>();
    private EditText find;

    private RelativeLayout hobby;
    private RelativeLayout computer;
    private RelativeLayout book;
    private RelativeLayout moise;
    private RelativeLayout gift;
    private RelativeLayout basketball;
    private RelativeLayout clothers;
    private RelativeLayout phone;
    private RelativeLayout cosmeticss;
    private RelativeLayout others;

    private int[] ids = new int[]{R.mipmap.green, R.mipmap.green2, R.mipmap.green3};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void setGoodsInfo() {
        for(int i =0;i<10;i++){
            GoodsInfoDescrib d1 = new GoodsInfoDescrib("666.0","","compute","asdasdasdasdasdasd","13:00");
            goodsInfoDescribList.add(d1);
            GoodsInfoDescrib d2 = new GoodsInfoDescrib("888.0","","asd","zxccvbmbmm","18:00");
            goodsInfoDescribList.add(d2);
        }
        list = goodsInfoDescribList;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_menu,container,false);
        initWight(rootView);
        setGoodsInfo();
//        Log.e("length",goodsInfoDescribList.size()+"");
        //计算手机当前的宽度;
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        Content.WIDTH = dm.widthPixels;

        mGroup = (ImageBarnnerFrameLayout) rootView.findViewById(R.id.image_group);
//        mGroup.setOnClickListener(this);

        List<Bitmap> list = new ArrayList<>();

        for (int i = 0; i < ids.length; i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), ids[i]);
            list.add(bitmap);
        }
        mGroup.addBitmaps(list);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        GoodsAdapter adapter = new GoodsAdapter(getContext(),goodsInfoDescribList);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    private void initWight(View rootView) {
        recyclerView = (RecyclerView) rootView.findViewById(R.id.goods_Info);
        find = (EditText) rootView.findViewById(R.id.main_find);
        find.setOnClickListener(this);

        hobby = (RelativeLayout) rootView.findViewById(R.id.main_hobby);
        computer = (RelativeLayout) rootView.findViewById(R.id.main_computer);
        book = (RelativeLayout) rootView.findViewById(R.id.main_books);
        moise = (RelativeLayout) rootView.findViewById(R.id.main_mouses);
        gift = (RelativeLayout) rootView.findViewById(R.id.main_gifts);
        basketball = (RelativeLayout) rootView.findViewById(R.id.main_basketball);
        clothers = (RelativeLayout) rootView.findViewById(R.id.main_clothes);
        phone = (RelativeLayout) rootView.findViewById(R.id.main_phones);
        cosmeticss = (RelativeLayout) rootView.findViewById(R.id.main_cosmeticss);
        others = (RelativeLayout) rootView.findViewById(R.id.main_others);

        hobby.setOnClickListener(this);
        computer.setOnClickListener(this);
        book.setOnClickListener(this);
        moise.setOnClickListener(this);
        gift.setOnClickListener(this);
        basketball.setOnClickListener(this);
        clothers.setOnClickListener(this);
        phone.setOnClickListener(this);
        cosmeticss.setOnClickListener(this);
        others.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void clickImageIndex(int pos) {
        Snackbar.make(recyclerView,""+pos,Snackbar.LENGTH_SHORT).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        Intent intentClassify = new Intent(getContext(), GoodsFilter.class);
        switch (v.getId()){
            case R.id.main_find:
                Intent intent = new Intent(getContext(), GoodsFind.class);
                startActivity(intent,ActivityOptions.makeSceneTransitionAnimation((Activity)getContext()).toBundle());
                break;
            case R.id.main_hobby:
                intentClassify.putExtra("classify","hobby");
                startActivity(intentClassify,ActivityOptions.makeSceneTransitionAnimation((Activity)getContext()).toBundle());
                break;
            case R.id.main_computer:
                intentClassify.putExtra("classify","computer");
                startActivity(intentClassify,ActivityOptions.makeSceneTransitionAnimation((Activity)getContext()).toBundle());
                break;
            case R.id.main_books:
                intentClassify.putExtra("classify","books");
                startActivity(intentClassify,ActivityOptions.makeSceneTransitionAnimation((Activity)getContext()).toBundle());
                break;
            case R.id.main_mouses:
                intentClassify.putExtra("classify","mouses");
                startActivity(intentClassify,ActivityOptions.makeSceneTransitionAnimation((Activity)getContext()).toBundle());
                break;
            case R.id.main_gifts:
                intentClassify.putExtra("classify","gifts");
                startActivity(intentClassify,ActivityOptions.makeSceneTransitionAnimation((Activity)getContext()).toBundle());
                break;
            case R.id.main_basketball:
                intentClassify.putExtra("classify","basketball");
                startActivity(intentClassify,ActivityOptions.makeSceneTransitionAnimation((Activity)getContext()).toBundle());
                break;
            case R.id.main_clothes:
                intentClassify.putExtra("classify","clothes");
                startActivity(intentClassify,ActivityOptions.makeSceneTransitionAnimation((Activity)getContext()).toBundle());
                break;
            case R.id.main_phones:
                intentClassify.putExtra("classify","phones");
                startActivity(intentClassify,ActivityOptions.makeSceneTransitionAnimation((Activity)getContext()).toBundle());
                break;
            case R.id.main_cosmeticss:
                intentClassify.putExtra("classify","cosmeticss");
                startActivity(intentClassify,ActivityOptions.makeSceneTransitionAnimation((Activity)getContext()).toBundle());
                break;
            case R.id.main_others:
                intentClassify.putExtra("classify","others");
                startActivity(intentClassify,ActivityOptions.makeSceneTransitionAnimation((Activity)getContext()).toBundle());
                break;
        }
    }
}
