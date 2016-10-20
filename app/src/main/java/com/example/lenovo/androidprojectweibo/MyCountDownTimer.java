package com.example.lenovo.androidprojectweibo;

import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * Created by lenovo on 2016/10/14.
 */

public class MyCountDownTimer extends CountDownTimer {
    TextView textView;
    public MyCountDownTimer(long millisInFuture, long countDownInterval,TextView textView) {
        super(millisInFuture, countDownInterval);
        this.textView=textView;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        textView.setText("倒计时(" + (millisUntilFinished+1000) / 1000 + ")");
        /*if(millisUntilFinished-1000==0){
            textView.setText("倒计时(" +1+")");
        }*/
    }

    @Override
    public void onFinish() {
        textView.setText("正在跳转");
    }
}
