package com.example.lenovo.androidprojectweibo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

import java.text.SimpleDateFormat;

/**
 * Created by lenovo on 2016/10/18.
 */

public class LoginActivity extends AppCompatActivity {
    EditText et_useName;
    EditText et_userPassword;
    Button login;
    Button register;
    Button login_sina;
    private AuthInfo authInfo;
    private Oauth2AccessToken mAccessToken;
    TextView tv_tokenView;
    sinaListener sinaListener;

    /** 注意：SsoHandler 仅当 SDK 支持 SSO 时有效 */
    private SsoHandler mSsoHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView();
        initial();
        setEvent();
        getToken();
    }

    private void setView() {

        setContentView(R.layout.activity_login);
    }
    public void initial(){
        et_useName= (EditText) findViewById(R.id.et_useName);
        et_userPassword= (EditText) findViewById(R.id.et_usePassword);
        login= (Button) findViewById(R.id.bt_login);
        register= (Button) findViewById(R.id.bt_register);
        login_sina= (Button) findViewById(R.id.bt_sinaLogin);
        tv_tokenView= (TextView) findViewById(R.id.tv_tokenView);
        authInfo = new AuthInfo(this, Constants.APP_KEY, Constants.REDIRECT_URL, Constants.SCOPE);
        mSsoHandler = new SsoHandler(LoginActivity.this, authInfo);
        sinaListener=new sinaListener(tv_tokenView,getApplicationContext());
    }
    public void setEvent(){
        login_sina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSsoHandler.authorize(sinaListener);

            }
        });
    }
    public void getToken(){
        mAccessToken = AccessTokenKeeper.readAccessToken(getApplicationContext());
        if (mAccessToken.isSessionValid()) {
                    updateTokenView(true);


        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mSsoHandler != null) {
            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }
    private void updateTokenView(boolean hasExisted) {
        String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(
                new java.util.Date(mAccessToken.getExpiresTime()));
        String format = getString(R.string.weibosdk_demo_token_to_string_format_1);
        tv_tokenView.setText(String.format(format, mAccessToken.getToken(), date));

        String message = String.format(format, mAccessToken.getToken(), date);
        if (hasExisted) {
            message = getString(R.string.weibosdk_demo_token_has_existed) + "\n" + message;
        }
        tv_tokenView.setText(message);
    }
}
