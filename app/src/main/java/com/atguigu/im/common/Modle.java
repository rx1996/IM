package com.atguigu.im.common;

import android.content.Context;

import com.atguigu.im.modle.bean.UserInfo;
import com.atguigu.im.modle.dao.AccountDAO;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2017/7/1.
 */

public class Modle {
    private AccountDAO accountDAO;
    private Modle(){}
    private Context context;
    private static Modle modle = new Modle();
    public static Modle getInstance(){
        return modle;
    }

    public void init(Context context){
        this.context = context;
        accountDAO = new AccountDAO(context);
        //初始化全局监听
        new GlobalListener(context);
    }

    private ExecutorService service = Executors.newCachedThreadPool();

    public ExecutorService getGlobalThread(){
        return service;
    }
    //登录成功后保存用户数据
    public void loginSuccess(UserInfo userInfo){
        //添加用户
        accountDAO.addAccount(userInfo);
    }
    public AccountDAO getAccountDAO(){
        return accountDAO;
    }
}
