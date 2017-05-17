package com.our_company.school_second_hand_shop.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.our_company.school_second_hand_shop.R;

/**
 * Created by Lance on 2017/5/7.
 */

public class ShowInfo extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_info);

        textView = (TextView) findViewById(R.id.show_info);
        String content = "";
        content = getIntent().getStringExtra("info");
        textView.setText(content);
    }
}
