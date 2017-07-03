package com.atguigu.im.controller.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.atguigu.im.R;
import com.atguigu.im.controller.activity.LoginActivity;
import com.atguigu.im.utils.UiUtils;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/3.
 */

public class SettingFragment extends Fragment {
    @Bind(R.id.setting_btn_exit)
    Button settingBtnExit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_settings, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //获取当前用户  1 数据库  2 环信api
        String currentUser = EMClient.getInstance().getCurrentUser();
        settingBtnExit.setText("退出登录 ("+currentUser+")");

        settingBtnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EMClient.getInstance().logout(false,new EMCallBack() {
                    @Override
                    public void onSuccess() {
                        //清除个人数据
                        UiUtils.showToast("退出登录");
                        //跳转到登录界面
                        startActivity(new Intent(getActivity(), LoginActivity.class));
                        //把当前界面finsh
                        getActivity().finish();
                    }

                    @Override
                    public void onError(int i, String s) {
                        UiUtils.showToast(s);
                    }

                    @Override
                    public void onProgress(int i, String s) {

                    }
                });
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
