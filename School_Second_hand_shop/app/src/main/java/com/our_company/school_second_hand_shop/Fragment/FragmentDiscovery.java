package com.our_company.school_second_hand_shop.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.our_company.school_second_hand_shop.DataClass.AdvertisementData;
import com.our_company.school_second_hand_shop.MyAdapter.ADListAdapter;
import com.our_company.school_second_hand_shop.OtherClass.SpaceItemDecoration;
import com.our_company.school_second_hand_shop.R;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by LZL on 2017/4/29.
 */

public class FragmentDiscovery extends Fragment {
    View root;
    List<AdvertisementData> dataList;
    RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_advertisement,container,false);
        initView();
        return root;
    }

    public void initView()
    {
        dataList = new LinkedList<>();
        dataList.add(new AdvertisementData(R.mipmap.donglanjianshen));
        dataList.add(new AdvertisementData(R.mipmap.huarun));
        dataList.add(new AdvertisementData(R.mipmap.weijialiangpi));
        dataList.add(new AdvertisementData(R.mipmap.yonghuichaoshi));
        dataList.add(new AdvertisementData(R.mipmap.aosikaguojiyingcheng));
        dataList.add(new AdvertisementData(R.mipmap.donglanjianshen));
        dataList.add(new AdvertisementData(R.mipmap.huarun));
        dataList.add(new AdvertisementData(R.mipmap.weijialiangpi));
        dataList.add(new AdvertisementData(R.mipmap.yonghuichaoshi));
        dataList.add(new AdvertisementData(R.mipmap.aosikaguojiyingcheng));
        recyclerView = (RecyclerView)root.findViewById(R.id.discovery_ad_list);
        ADListAdapter adListAdapter = new ADListAdapter(dataList);
        recyclerView.setAdapter(adListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new SpaceItemDecoration(30));
    }
}
