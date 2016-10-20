package com.example.lenovo.androidprojectweibo;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.lenovo.androidprojectweibo.models.ErrorInfo;
import com.example.lenovo.androidprojectweibo.models.User;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.utils.LogUtil;

/**
 * Created by lenovo on 2016/10/20.
 */

public class UserRequestListener implements RequestListener {
    private static final String TAG ="UserInfo_Activity" ;
    Context context;
    User user;

    public UserRequestListener() {
    }

    public UserRequestListener(Context context,User user) {
        this.context = context;
        this.user=user;
    }

    @Override
    public void onComplete(String s) {
        if(!TextUtils.isEmpty(s)){
            User user_parse=User.parse(s);
            if(user_parse!=null){
                Toast.makeText(context, "用户存在", Toast.LENGTH_SHORT).show();
                user=user_parse;
            }else{
                Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
                user=null;
            }
        }
    }

    @Override
    public void onWeiboException(WeiboException e) {
        LogUtil.e(TAG, e.getMessage());
        ErrorInfo info = ErrorInfo.parse(e.getMessage());
        Toast.makeText(context, info.toString(), Toast.LENGTH_LONG).show();
    }

    public User getUser() {
        return user;
    }
}
