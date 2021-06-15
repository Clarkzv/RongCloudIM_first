package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

public class MainActivity extends AppCompatActivity {

    private String TAG = ".MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String appKey = "cpj2xarlcm9wn";
        //第一个参数必须传应用上下文
        RongIM.init(getApplication(), appKey);

        String token = "EfnwQcf7lGLVvgNbVCo0ekU69V99y9eE@sm5u.cn.rongnav.com;sm5u.cn.rongcfg.com";
        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onDatabaseOpened(RongIMClient.DatabaseOpenStatus code) {
                //消息数据库打开，可以进入到主页面
                Log.d(TAG, "onDatabaseOpened: 消息数据库打开，可以进入到主页面");
            }

            @Override
            public void onSuccess(String s) {
                //连接成功
                Log.d(TAG, "onSuccess: 连接成功");
            }

            @Override
            public void onError(RongIMClient.ConnectionErrorCode errorCode) {
                if(errorCode.equals(RongIMClient.ConnectionErrorCode.RC_CONN_TOKEN_INCORRECT)) {
                    //从 APP 服务获取新 token，并重连
                    Log.d(TAG, "onError: 从 APP 服务获取新 token，并重连");
                } else {
                    //无法连接 IM 服务器，请根据相应的错误码作出对应处理
                    Log.d(TAG, "onError: 无法连接 IM 服务器，请根据相应的错误码作出对应处理");
                }
            }
        });


    }
}