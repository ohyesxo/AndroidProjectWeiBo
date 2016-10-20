package com.example.lenovo.androidprojectweibo;

import android.content.ClipData;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.androidprojectweibo.models.ErrorInfo;
import com.example.lenovo.androidprojectweibo.models.User;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.exception.WeiboException;


/**
 * Created by lenovo on 2016/10/20.
 */

public class UserInfo extends AppCompatActivity {
    private static final String TAG =UserInfo.class.getName() ;
    TextView tv_userId,tv_fansNumber,tv_focusNumber,tv_wbNumber;
    private Oauth2AccessToken mAccessToken;
    UserRequestListener userRequestListener;
    User user;

    /** 用户信息接口 */
    private UsersAPI mUsersAPI;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView();
        initial();
        setTextView();
    }
public void setView(){
    setContentView(R.layout.activity_userinfo);
}
    public void initial(){
        tv_userId= (TextView) findViewById(R.id.tv_userId);
        tv_fansNumber= (TextView) findViewById(R.id.tv_fansNumber);
        tv_focusNumber= (TextView) findViewById(R.id.tv_focusNumber);
        tv_wbNumber= (TextView) findViewById(R.id.tv_wbNumber);
        mAccessToken = AccessTokenKeeper.readAccessToken(this);
        // 获取用户信息接口
        mUsersAPI = new UsersAPI(this, Constants.APP_KEY, mAccessToken);
        userRequestListener=new UserRequestListener();
        mUsersAPI.show(Long.parseLong(mAccessToken.getUid()),userRequestListener);
        user=userRequestListener.getUser();

    }
    public void setTextView(){
        if(user==null){
            tv_userId.setText("用户ID:");
            tv_fansNumber.setText("粉丝数:");
            tv_focusNumber.setText("关注数:");
            tv_wbNumber.setText("微博数:");
        }else{
        tv_userId.setText("用户ID:"+user.id);
        tv_fansNumber.setText("粉丝数:"+user.followers_count);
        tv_focusNumber.setText("关注数:"+user.friends_count);
        tv_wbNumber.setText("微博数:"+user.statuses_count);
    }}
}
