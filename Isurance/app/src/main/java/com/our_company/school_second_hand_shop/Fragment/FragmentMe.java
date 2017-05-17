package com.our_company.school_second_hand_shop.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.our_company.school_second_hand_shop.Activity.CarInfo;
import com.our_company.school_second_hand_shop.Activity.Login_Activity;
import com.our_company.school_second_hand_shop.Activity.UserInfo;
import com.our_company.school_second_hand_shop.R;
import static com.our_company.school_second_hand_shop.Content.*;

/**
 * Created by Lance on 2017/5/9.
 */

public class FragmentMe extends Fragment implements  View.OnClickListener{

    private RelativeLayout loginIn;
    private LinearLayout userInfo;
    private LinearLayout carInfo;
    private LinearLayout userHelp;
    private LinearLayout userQuestion;
    private TextView isLogin;
    private Button button;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me,container,false);
        initWight(view);
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case LOGININ_SUCCESS:
                        isLogin.setText("已登录");
                        loginIn.setOnClickListener(null);
                        button.setText("注销登陆");
                        break;
                    case LOGIN_OUT:
                        isLogin.setText("登陆/注册");
                        loginIn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent1 = new Intent(getContext(), Login_Activity.class);
                                startActivity(intent1);
                            }
                        });
                        button.setText("退出系统");
                        break;
                }
            }
        };
        return view;
    }

    private void initWight(View view) {
        loginIn = (RelativeLayout) view.findViewById(R.id.me_login);
        userInfo = (LinearLayout) view.findViewById(R.id.user_info);
        carInfo = (LinearLayout) view.findViewById(R.id.car_info);
        userHelp = (LinearLayout) view.findViewById(R.id.user_help);
        userQuestion = (LinearLayout) view.findViewById(R.id.use_question);
        isLogin = (TextView) view.findViewById(R.id.islogin);
        button = (Button) view.findViewById(R.id.me_loginout);


        if(isLoginIn == 1){
            isLogin.setText("已登录");
            loginIn.setOnClickListener(null);
            button.setText("退出登陆");
        }else {
            isLogin.setText("登陆/注册");
            loginIn.setOnClickListener(this);
            button.setText("退出系统");
        }
        userInfo.setOnClickListener(this);
        carInfo.setOnClickListener(this);
        userHelp.setOnClickListener(this);
        userQuestion.setOnClickListener(this);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.me_login:
                Intent intent1 = new Intent(getContext(), Login_Activity.class);
                startActivity(intent1);
                break;
            case R.id.user_info:
                if(isLoginIn == 1){
                    Intent intent = new Intent(getContext(), UserInfo.class);
                    startActivity(intent);
                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("温馨提醒：");
                    builder.setMessage("您还未登陆,请先登陆!");
                    builder.setPositiveButton("好的",null);
                    builder.create().show();
                }
                break;
            case R.id.car_info:
                if(isLoginIn == 1){
                    Intent intent2 = new Intent(getContext(), CarInfo.class);
                    startActivity(intent2);
                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("温馨提醒：");
                    builder.setMessage("您还未登陆,请先登陆!");
                    builder.setPositiveButton("好的",null);
                    builder.create().show();
                }
                break;
            case R.id.user_help:
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("程序说明：");
                builder.setMessage("该APP是用来进行各种车险的购买平台.");
                builder.setPositiveButton("好的",null);
                builder.create().show();
                break;
            case R.id.use_question:
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                builder1.setTitle("问题反馈：");
                builder1.setPositiveButton("好的",null);
                builder1.setMessage("请发邮件至邮箱：XXXXXXX@XXXX.com");
                builder1.create().show();
                break;
            case R.id.me_loginout:
                if(isLoginIn == 1){
                    AlertDialog.Builder builder2 = new AlertDialog.Builder(getContext());
                    builder2.setTitle("温馨提醒：");
                    builder2.setMessage("是否注销当前用户？");
                    builder2.setPositiveButton("注销", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            isLoginIn = 0;
                            Message message = new Message();
                            message.what = LOGIN_OUT;
                            handler.sendMessage(message);
                        }
                    });
                    builder2.setNegativeButton("取消",null);
                    builder2.create().show();
                }else{
                    AlertDialog.Builder builder2 = new AlertDialog.Builder(getContext());
                    builder2.setTitle("温馨提醒：");
                    builder2.setMessage("是否退出？");
                    builder2.setPositiveButton("退出", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            System.exit(0);
                        }
                    });
                    builder2.setNegativeButton("取消",null);
                    builder2.create().show();
                }
                break;
            default:
                break;
        }
    }
}
