package com.atguigu.im.controller.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.atguigu.im.R;
import com.atguigu.im.base.BaseActivity;
import com.atguigu.im.common.Modle;
import com.atguigu.im.controller.adapter.InviteAdapter;
import com.atguigu.im.modle.bean.InvitationInfo;
import com.atguigu.im.utils.SPUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class InviteActivity extends BaseActivity {

    @Bind(R.id.lv_invite)
    ListView lvInvite;
    private InviteAdapter adapter;

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        //设置小红点的状态
        SPUtils.getSpUtils().save(SPUtils.NEW_INVITE,false);
        adapter = new InviteAdapter(this,onInviteListener);
        lvInvite.setAdapter(adapter);
        refreshData();//给适配器设置数据
    }
    /*

    界面数据展示需要考虑三大数据源

    * 网络
    *
    *本地
    *
    *内存和页面
    *
    *
    * */
    public void refreshData(){
        //从数据库获取数据
        List<InvitationInfo> invitations = Modle.getInstance().getHelperManager().getInvitationDAO().getInvitations();
        if(invitations != null) {
            adapter.refresh(invitations);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_invite;
    }
    //inviteAdapter的回调方法
    InviteAdapter.OninviteListener onInviteListener = new InviteAdapter.OninviteListener() {
        @Override
        public void invitedSuccess(InvitationInfo info) {

        }

        @Override
        public void invitedReject(InvitationInfo info) {

        }
    };
}
