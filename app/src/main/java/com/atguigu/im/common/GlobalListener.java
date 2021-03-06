package com.atguigu.im.common;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.atguigu.im.modle.bean.InvitationInfo;
import com.atguigu.im.modle.bean.UserInfo;
import com.atguigu.im.utils.SPUtils;
import com.hyphenate.EMContactListener;
import com.hyphenate.chat.EMClient;

/**
 * Created by Administrator on 2017/7/3.
 */

public class GlobalListener {
    private  LocalBroadcastManager manager;
    public GlobalListener(Context context){
        EMClient.getInstance().contactManager().setContactListener(emContactListener);
        //本地广播
        /*
        * 本地广播和全局广播的区别？
        * 本地广播 :  只有本应用可以收到
        * 全局广播 :  所有应用都可以收到
        * */
        manager = LocalBroadcastManager.getInstance(context);
    }
    //设置全局监听
    EMContactListener emContactListener = new EMContactListener() {

        //收到好友邀请
        @Override
        public void onContactInvited(String username, String reason) {
            InvitationInfo invitationInfo = new InvitationInfo();
            invitationInfo.setReason(reason);
            invitationInfo.setUserInfo(new UserInfo(username,username));
            invitationInfo.setStatus(InvitationInfo.InvitationStatus.NEW_INVITE);
            //添加InvitationInfo
            Modle.getInstance().getHelperManager().getInvitationDAO().addInvitation(invitationInfo);
            //保存小红点
            SPUtils.getSpUtils().save(SPUtils.NEW_INVITE,true);
            //发送广播
            manager.sendBroadcast(new Intent(Constant.NEW_INVITE_CHANGE));
        }

        //好友请求被同意  你加别人的时候 别人同意了
        @Override
        public void onContactAgreed(String username) {
            //添加邀请信息
            InvitationInfo invitationInfo = new InvitationInfo();
            invitationInfo.setUserInfo(new UserInfo(username,username));
            invitationInfo.setReason("邀请被接受");
            invitationInfo.setStatus(InvitationInfo.InvitationStatus.INVITE_ACCEPT);
            Modle.getInstance().getHelperManager().getInvitationDAO().addInvitation(invitationInfo);
            //保存小红点的状态
            SPUtils.getSpUtils().save(SPUtils.NEW_INVITE,true);
            //发送广播
            manager.sendBroadcast(new Intent(Constant.NEW_INVITE_CHANGE));

        }



        //被删除时回调此方法
        @Override
        public void onContactDeleted(String username) {
            //删除邀请信息
            Modle.getInstance().getHelperManager().getInvitationDAO().removeInvitation(username);
            //删除联系人
            Modle.getInstance().getHelperManager().getContactDAO().deleteContactByHxId(username);
            //发送广播
            manager.sendBroadcast(new Intent(Constant.CONTACT_CHANGE));
        }


        //增加了联系人时回调此方法  当你同意添加好友
        @Override
        public void onContactAdded(String username) {
            //添加联系人
            Modle.getInstance().getHelperManager().getContactDAO().saveContact(new UserInfo(username,username),true);
            //发送广播
            manager.sendBroadcast(new Intent(Constant.CONTACT_CHANGE));
        }

        //好友请求被拒绝  你加别人 别人拒绝了
        @Override
        public void onContactRefused(String username) {
            //保存小红点
            SPUtils.getSpUtils().save(SPUtils.NEW_INVITE,true);
            //发送广播
            manager.sendBroadcast(new Intent(Constant.NEW_INVITE_CHANGE));
        }
    };
}
