package com.mlxing.mlguide.utils;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mlxing.mlguide.activity.html5.WebViewActivity;
import com.mlxing.mlguide.application.AppApplication;


public class UIHelp {

    /**
     * 弹出Toast消息
     *
     * @param msg
     */
    public static void toastMessage(String msg) {
        Toast.makeText(AppApplication.context, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 弹出Toast消息
     *
     * @param cont
     * @param msg
     */
    public static void toastMessage(Context cont, int msg) {
        Toast.makeText(cont, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 弹出Toast消息
     *
     * @param cont
     * @param msg
     * @param time
     */
    public static void toastMessage(Context cont, String msg, int time) {
        Toast.makeText(cont, msg, time).show();
    }

    /**
     * 跳转到webView界面
     *
     * @param url
     */
    public static void showWebViewActivity(Context context,String title, String url) {
        Intent intent = new Intent(AppApplication.context, WebViewActivity.class);
        intent.putExtra(WebViewActivity.PARAM_URL, url);
        intent.putExtra(WebViewActivity.PARAM_TITLE, title);
        context.startActivity(intent);
    }

    public static void setImgUrl(ImageView imgview, String url){
        Glide.with(AppApplication.context).load(url).into(imgview);
    }
}
