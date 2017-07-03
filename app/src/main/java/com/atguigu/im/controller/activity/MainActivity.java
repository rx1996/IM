package com.atguigu.im.controller.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.atguigu.im.R;
import com.atguigu.im.base.BaseActivity;
import com.atguigu.im.controller.fragment.ContactListFragment;
import com.atguigu.im.controller.fragment.ConversationFragment;
import com.atguigu.im.controller.fragment.SettingFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @Bind(R.id.main_fl)
    FrameLayout mainFl;
    @Bind(R.id.rb_main_conversation)
    RadioButton rbMainConversation;
    @Bind(R.id.rb_main_contact)
    RadioButton rbMainContact;
    @Bind(R.id.rb_main_setting)
    RadioButton rbMainSetting;
    @Bind(R.id.rg_main)
    RadioGroup rgMain;
    @Bind(R.id.activity_main)
    LinearLayout activityMain;

    @Override
    public void initListener() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switchFragment(checkedId);
            }
        });
    }

    private void switchFragment(int checkedId) {
        Fragment fragment = null;
        switch (checkedId){
            case R.id.rb_main_contact:
                fragment = new ContactListFragment();
                break;
            case R.id.rb_main_conversation:
                fragment = new ConversationFragment();
                break;
            case R.id.rb_main_setting:
                fragment = new SettingFragment();
                break;
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fl,fragment)
                .commit();
    }

    @Override
    public void initData() {
        switchFragment(R.id.rb_main_conversation);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }
}
