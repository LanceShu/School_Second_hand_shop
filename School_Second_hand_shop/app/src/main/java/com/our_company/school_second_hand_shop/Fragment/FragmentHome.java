package com.our_company.school_second_hand_shop.Fragment;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.our_company.school_second_hand_shop.Activity.GoodsFilter;
import com.our_company.school_second_hand_shop.Activity.GoodsFind;
import com.our_company.school_second_hand_shop.Activity.IssueGoods;
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

    public static final int LOADING = 1;
    public static final int REFRESHING = 2;
    public static final int START = 3;

    private ImageBarnnerFrameLayout mGroup;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<GoodsInfoDescrib> goodsInfoDescribList = new ArrayList<>();
    private EditText find;

    private FloatingActionButton fab;
    private TextView cdl;
    private LinearLayout fab2_layout;
    private boolean fabOpened ;

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

    private GoodsAdapter adapter;
    private GridLayoutManager gridLayoutManager;

    private int[] ids = new int[]{R.mipmap.green, R.mipmap.green2, R.mipmap.green3};

    private int lastVisibleitem = 0;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case LOADING:
                    addData();
                    adapter = new GoodsAdapter(getContext(),goodsInfoDescribList);
                    recyclerView.setAdapter(adapter);
                    recyclerView.scrollToPosition(lastVisibleitem);
                    swipeRefreshLayout.setRefreshing(false);
                    break;
                case START:
                    setGoodsInfo();
                    adapter = new GoodsAdapter(getContext(),goodsInfoDescribList);
                    recyclerView.setAdapter(adapter);
                    swipeRefreshLayout.setRefreshing(false);
                    break;
                case REFRESHING:
                    setGoodsInfo();
                    adapter = new GoodsAdapter(getContext(),goodsInfoDescribList);
                    recyclerView.setAdapter(adapter);
                    swipeRefreshLayout.setRefreshing(false);
                    break;
                default:
                    break;
            }
        }
    };

    private void addData() {
        for(int i =0;i<10;i++){
            GoodsInfoDescrib d1 = new GoodsInfoDescrib("7777.0","","lance","lancelance","13:00");
            goodsInfoDescribList.add(d1);
            GoodsInfoDescrib d2 = new GoodsInfoDescrib("9999.0","","asd","zxccvbmbmm","18:00");
            goodsInfoDescribList.add(d2);
        }
        list = goodsInfoDescribList;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fabOpened = false;
        goodsInfoDescribList.clear();
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
        View rootView = inflater.inflate(R.layout.fragment_home,container,false);
//        setGoodsInfo();
        initWight(rootView);
//        Log.e("length",goodsInfoDescribList.size()+"");
        //计算手机当前的宽度;
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        Content.WIDTH = dm.widthPixels;

        mGroup = (ImageBarnnerFrameLayout) rootView.findViewById(R.id.image_group);
        mGroup.setOnClickListener(this);

        List<Bitmap> list = new ArrayList<>();

        for (int i = 0; i < ids.length; i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), ids[i]);
            list.add(bitmap);
        }
        mGroup.addBitmaps(list);
        gridLayoutManager = new GridLayoutManager(getContext(),2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
//        adapter = new GoodsAdapter(getContext(),goodsInfoDescribList);
//        recyclerView.setAdapter(adapter);
        return rootView;
    }

    private void initWight(View rootView) {
        recyclerView = (RecyclerView) rootView.findViewById(R.id.goods_Info);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleitem + 1==adapter.getItemCount()){
                    swipeRefreshLayout.setRefreshing(true);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Message message = new Message();
                            message.what = LOADING;
                            handler.sendMessage(message);
                        }
                    },500);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleitem = gridLayoutManager.findLastVisibleItemPosition();
            }
        });
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swip_refresh_layout);
        swipeRefreshLayout.setColorSchemeResources(R.color.register_back_other,R.color.colorAccent,R.color.register_background,R.color.cardview_dark_background);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = START;
                        handler.sendMessage(message);
                    }
                },1000);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Message message  = new Message();
                        message.what = REFRESHING;
                        handler.sendMessage(message);
                    }
                },3500);
            }
        });
        swipeRefreshLayout.setProgressViewOffset(false,0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,24,getResources().getDisplayMetrics()));

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
        fab = (FloatingActionButton) rootView.findViewById(R.id.fab1);
        cdl = (TextView) rootView.findViewById(R.id.cdl);
        fab2_layout = (LinearLayout) rootView.findViewById(R.id.fab2_layout);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!fabOpened){
                    openMenu(v);
                }else{
                    closeMune(v);
                }
            }
        });

        fab2_layout.setOnClickListener(this);
        cdl.setOnClickListener(this);
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

        handler.sendEmptyMessageDelayed(0,3000);
    }

    private void closeMune(View v) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.9f,0);
        alphaAnimation.setDuration(500);
        cdl.startAnimation(alphaAnimation);
        AlphaAnimation alphaAnimation1 = new AlphaAnimation(1.0f,0);
        alphaAnimation1.setDuration(500);
        fab2_layout.startAnimation(alphaAnimation1);
        cdl.setVisibility(View.GONE);
        fab2_layout.setVisibility(View.GONE);
        ObjectAnimator animator = ObjectAnimator.ofFloat(v,"rotation",-135,20,0);
        animator.setDuration(500);
        animator.start();
        fabOpened = false;
    }

    private void openMenu(View v) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(v,"rotation",0,-155,-135);
        objectAnimator.setDuration(500);
        objectAnimator.start();
        cdl.setVisibility(View.VISIBLE);
        fab2_layout.setVisibility(View.VISIBLE);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0,0.9f);
        alphaAnimation.setDuration(500);
        alphaAnimation.setFillAfter(true);
        cdl.startAnimation(alphaAnimation);
        AlphaAnimation alphaAnimation1 = new AlphaAnimation(0,1.0f);
        alphaAnimation1.setDuration(500);
        fab2_layout.startAnimation(alphaAnimation1);
        fabOpened = true;
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
            case R.id.cdl:
                    closeMune(fab);
                break;
            case R.id.fab2_layout:

                Intent intent1 = new Intent(getContext(), IssueGoods.class);
                startActivity(intent1,ActivityOptions.makeSceneTransitionAnimation((Activity) getContext()).toBundle());
                closeMune(fab);
                break;
        }
    }

}
