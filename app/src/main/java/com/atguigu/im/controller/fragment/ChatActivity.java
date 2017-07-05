package com.atguigu.im.controller.fragment;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.atguigu.im.R;
import com.atguigu.im.base.BaseActivity;
import com.hyphenate.easeui.ui.EaseChatFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ChatActivity extends BaseActivity {

    @Bind(R.id.chat_fl)
    FrameLayout chatFl;

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        EaseChatFragment chatFragment = new EaseChatFragment();
        chatFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().replace(R.id.chat_fl,chatFragment).commit();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_chat;
    }
}
