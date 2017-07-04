package com.atguigu.im.controller.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.atguigu.im.R;
import com.atguigu.im.base.BaseActivity;
import com.atguigu.im.controller.adapter.InviteAdapter;
import com.atguigu.im.modle.bean.InvitationInfo;

import butterknife.Bind;
import butterknife.ButterKnife;

public class InviteActivity extends BaseActivity {

    @Bind(R.id.lv_invite)
    ListView lvInvite;

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        InviteAdapter adapter = new InviteAdapter(this,onInviteListener);
        lvInvite.setAdapter(adapter);
    }
    public void refreshData(){

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
