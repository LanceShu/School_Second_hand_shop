package com.our_company.school_second_hand_shop.MyAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.our_company.school_second_hand_shop.Activity.IssueGoods;
import com.our_company.school_second_hand_shop.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lance on 2017/4/26.
 */

public class ExpandableListView extends BaseExpandableListAdapter {

    private Map<String,List<String>> dataset = new HashMap<>();
    private String s ;
    private Context context;

    public ExpandableListView(Map<String,List<String>> dataset,String  t,Context context){
        this.dataset = dataset;
        s = t;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return dataset.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return dataset.get(s).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return dataset.get(s);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return dataset.get(s).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.parent_item,null);
        }
        convertView.setTag(R.layout.parent_item,groupPosition);
        convertView.setTag(R.layout.child_item,-1);
        TextView textView = (TextView) convertView.findViewById(R.id.parent_title);
        textView.setText(s);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.child_item, null);
        }
        convertView.setTag(R.layout.parent_item, groupPosition);
        convertView.setTag(R.layout.child_item, groupPosition);
        TextView text = (TextView) convertView.findViewById(R.id.child_title);
        text.setText(dataset.get(s).get(childPosition));
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "点到了内置的textview", Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}