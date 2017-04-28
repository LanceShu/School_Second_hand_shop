package com.our_company.school_second_hand_shop.Activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.our_company.school_second_hand_shop.MyAdapter.ExpandableListViewAdapter;
import com.our_company.school_second_hand_shop.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lance on 2017/4/26.
 */

public class IssueGoods extends AppCompatActivity {

    private ExpandableListView elv1;
    private ExpandableListView elv2;

    private List<String> classify = new ArrayList<>();
    private List<String> area = new ArrayList<>();
    Map<String,List<String>> dataset1;
    Map<String,List<String>> dataset2;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        Fade fade = new Fade();
        fade.setDuration(250);
        getWindow().setEnterTransition(fade);
        setContentView(R.layout.goods_issue);
        initWight();                //实例化控件

        setClassify();              //设置分类
        setArea();
    }

    private void setArea() {
        dataset2 = new HashMap<>();
        area.add("西北工业大学");
        area.add("陕西师范大学");
        area.add("西安邮电大学");
        area.add("西安理工大学");
        area.add("西安外国语大学");
        area.add("西北政法大学");
        dataset2.put("学校",area);
        ExpandableListViewAdapter adapter = new ExpandableListViewAdapter(dataset2,"学校",this);
        elv2.setAdapter(adapter);
    }

    private void setClassify() {
        dataset1 = new HashMap<>();
        classify.add("兴趣爱好");
        classify.add("电脑");
        classify.add("图书素材");
        classify.add("鼠标配件");
        classify.add("收藏礼品");
        classify.add("运动健身");
        classify.add("衣服鞋帽");
        classify.add("手机");
        classify.add("保养化妆");
        classify.add("其他");
        dataset1.put("分类",classify);
        ExpandableListViewAdapter adapter = new ExpandableListViewAdapter(dataset1,"分类",this);
        elv1.setAdapter(adapter);
    }

    private void initWight() {
        elv1 = (ExpandableListView) findViewById(R.id.issue_auto1);
        elv2 = (ExpandableListView) findViewById(R.id.issue_auto2);

        elv1.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(IssueGoods.this,classify.get(childPosition),Toast.LENGTH_SHORT).show();
                Log.e("11111111111",dataset1.get("分类").get(childPosition));
                return true;
            }
        });
    }

}
