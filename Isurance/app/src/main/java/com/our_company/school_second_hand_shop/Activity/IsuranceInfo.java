package com.our_company.school_second_hand_shop.Activity;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.our_company.school_second_hand_shop.OtherClass.HttpGet;
import com.our_company.school_second_hand_shop.R;

/**
 * Created by Lance on 2017/5/8.
 */

public class IsuranceInfo extends AppCompatActivity implements View.OnClickListener{
    private ImageView back;
    private WebView webView;
    private Handler handler = new Handler();
    private ProgressBar progressBar;            //加载条;

    private String uri = "";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        Fade explode = new Fade();
        explode.setDuration(500);
        getWindow().setEnterTransition(explode);
        setContentView(R.layout.isurance_info);

        uri = getIntent().getStringExtra("uri");
        initWight();
    }

    private void initWight() {
        back = (ImageView) findViewById(R.id.goodsBack);
        back.setOnClickListener(this);
        webView = (WebView) findViewById(R.id.isurance_web);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(uri);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
            }
        },2000);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.goodsBack:
                finish();
                break;
        }
    }
}
