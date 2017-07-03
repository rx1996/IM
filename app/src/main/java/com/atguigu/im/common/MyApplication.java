package com.atguigu.im.common;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.atguigu.im.R;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.controller.EaseUI;

public class MyApplication extends Application {
    private static Context context;
    private static Handler handler;
    private static int pid;

    public static Context getContext(){
        return context;
    }
    public static Handler getHandler(){
        return handler;
    }
    public static int getPid(){
        return pid;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化环信sdk
        EMOptions options = new EMOptions();//配置一些功能
        //是否自动接受群组邀请
        options.setAutoAcceptGroupInvitation(false);
        //是否自动接受邀请
        options.setAcceptInvitationAlways(false);
        EaseUI.getInstance().init(this,options);

        //初始化modle
        Modle.getInstance().init(this);

        handler = new Handler();
        pid = android.os.Process.myPid();
        context = this;
    }
}
