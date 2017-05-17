package com.our_company.school_second_hand_shop.OtherClass;

import android.os.Handler;
import android.webkit.WebView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by Lance on 2016/11/24.
 */
public class HttpGet extends Thread {

    private String url;

    private WebView webView;

    private Handler handler;

    public HttpGet(String url, WebView webView, Handler handler){
        this.url = url;
        this.webView = webView;
        this.handler = handler;
    }

    @Override
    public void run() {
        try {
            URL httpurl = new URL(url);
            try {
                HttpURLConnection conn = (HttpURLConnection) httpurl.openConnection();
                conn.setReadTimeout(5000);
                conn.setRequestMethod("GET");
                final StringBuffer sb = new StringBuffer();
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String str = "";
                while ((str = reader.readLine()) != null) {
                    sb.append(str);
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        webView.loadData(sb.toString(), "text/html;charset=utf-8", null);
                    }
                });
            }catch (Exception e){
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
