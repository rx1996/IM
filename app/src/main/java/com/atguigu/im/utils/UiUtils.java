package com.atguigu.im.utils;

import android.content.Context;
import android.os.Process;
import android.provider.ContactsContract;
import android.widget.Toast;

import com.atguigu.im.common.MyApplication;

/**
 * Created by Administrator on 2017/7/3.
 */

public class UiUtils {
    public static Context getContext(){
        return MyApplication.getContext();
    }

    //保证runnable在主线程中运行
    public static void UIThread(Runnable runnable){
        if(MyApplication.getPid() == Process.myTid()) {
            runnable.run();
        }else {
            MyApplication.getHandler().post(runnable);
        }
    }
    //吐司
    public static void showToast(final String message){
        UIThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
