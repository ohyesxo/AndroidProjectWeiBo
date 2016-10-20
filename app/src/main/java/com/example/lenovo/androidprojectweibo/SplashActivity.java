package com.example.lenovo.androidprojectweibo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by lenovo on 2016/10/14.
 * 引导页面
 * 未完成部分,对是否为登陆用户进行判断,如果是则不显示引导页面,如果不是则显示或如果是登陆用户则显示guide
 */

public class SplashActivity extends AppCompatActivity implements View.OnClickListener{
    TextView textView;//时间跳转的控件
    Handler handler;
    MyCountDownTimer myCountDownTimer;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView();
        initial();
        setEvent();
        goToMain();
    }
    public void setView(){
        setContentView(R.layout.splash_activity);
    }
    public void initial(){//初始化控件
        textView= (TextView) findViewById(R.id.splash_time);
    }
    public void setEvent(){//设置事件
        textView.setOnClickListener(this);
        handler=new Handler();
    }
    public void goToMain(){
        myCountDownTimer=new MyCountDownTimer(3000,500,textView);
        myCountDownTimer.start();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(isFinishing()){
                    return;
                };
                changeActivity();
            }
        }, 3000);
    }
    public void changeActivity(){
        finish();
        Intent intent=new Intent(SplashActivity.this,LoginActivity.class);
        startActivity(intent);
    }
/*
* 重写点击事件,直接跳转到主界面
*
* */
    @Override
    public void onClick(View v) {
        changeActivity();
    }
}
