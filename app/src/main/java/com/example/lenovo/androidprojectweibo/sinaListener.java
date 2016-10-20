package com.example.lenovo.androidprojectweibo;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.exception.WeiboException;

import java.text.SimpleDateFormat;



/**
 * Created by lenovo on 2016/10/18.
 */

public class sinaListener implements WeiboAuthListener {
    Oauth2AccessToken accessToken;
    TextView tokenView;
    Context context;

    public sinaListener(TextView tokenView,Context context) {
        this.tokenView = tokenView;
        this.context=context;
    }

    @Override
    public void onComplete(Bundle bundle) {
         accessToken=Oauth2AccessToken.parseAccessToken(bundle);//获取用户信息?
        if(accessToken!=null&&accessToken.isSessionValid()){
            String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(
                    new java.util.Date(accessToken.getExpiresTime()));
           /* String format = bundle.getString(R.string.weibosdk_demo_token_to_string_format_1);*/
            tokenView.setText(String.format(accessToken.getToken(), date));
            AccessTokenKeeper.writeAccessToken(context,accessToken);
        }else{
            String code=bundle.getString("code",null);
            tokenView.setText(code);
        }
    }




    @Override
    public void onWeiboException(WeiboException e) {
        Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCancel() {
        Toast.makeText(context,"取消", Toast.LENGTH_SHORT).show();
    }
}
