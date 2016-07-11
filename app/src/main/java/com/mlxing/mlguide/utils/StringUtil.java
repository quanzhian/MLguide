package com.mlxing.mlguide.utils;

/**
 * Created by quan on 2016/6/17.
 */
public final class StringUtil {

    public static boolean empty(String str){
        return str == null || str.isEmpty() || str.length() <= 0;
    }

    public static String stringValue(String str , String def){
        if(empty(str)){
            return def;
        }
        return str;
    }

}
