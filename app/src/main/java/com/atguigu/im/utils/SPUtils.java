package com.atguigu.im.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2017/7/4.
 */

public class SPUtils {
    private SharedPreferences sp;
    private SPUtils(){}
    private static SPUtils spUtils = new SPUtils();

    public static SPUtils getSpUtils(){
        return spUtils;
    }

    public void init(Context context,String name){
        sp = context.getSharedPreferences(name,Context.MODE_PRIVATE);
    }

    public void save(String key,Object value){
        SharedPreferences.Editor editor = sp.edit();
        if(value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value).commit();
        }
        if(value instanceof String) {
            editor.putString(key, (String) value).commit();
        }
    }
    public Boolean getBolValue(String key){
        return sp.getBoolean(key,false);
    }
}
