package com.our_company.school_second_hand_shop.Activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.view.Window;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

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
    private ExpandableListView elv3;

    private Map<String,List<String>> dataset = new HashMap<>();
    private List<String> classify = new ArrayList<>();
    private List<String> area = new ArrayList<>();
    private List<String> school = new ArrayList<>();

    private com.our_company.school_second_hand_shop.MyAdapter.ExpandableListView adapter;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        Fade fade = new Fade();
        fade.setDuration(250);
        getWindow().setEnterTransition(fade);
        setContentView(R.layout.goods_issue);
        initWight();
        setData();
        adapter = new com.our_company.school_second_hand_shop.MyAdapter.ExpandableListView(dataset,"class",this);
        elv1.setAdapter(adapter);
        elv2.setAdapter(adapter);
        elv3.setAdapter(adapter);
    }

    private void setData() {
        classify.add("frist");
        classify.add("sconde");
        classify.add("thrid");
        dataset.put("class",classify);
    }

    private void initWight() {
        elv1 = (ExpandableListView) findViewById(R.id.issue_auto1);
        elv2 = (ExpandableListView) findViewById(R.id.issue_auto2);
        elv3 = (ExpandableListView) findViewById(R.id.issue_auto3);
    }

}
