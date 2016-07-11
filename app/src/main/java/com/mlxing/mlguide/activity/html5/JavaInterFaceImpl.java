package com.mlxing.mlguide.activity.html5;

import android.content.Context;
import android.webkit.JavascriptInterface;

import com.mlxing.mlguide.utils.UIHelp;

/**
 * JavaScript注入回调
 * Created by quan on 2016/6/18.
 */
public class JavaInterFaceImpl {

    private Context context;
    private String TAG;

    public JavaInterFaceImpl(Context context){
        this.context = context;
        this.TAG = this.getClass().getSimpleName();
    }

    @JavascriptInterface
    public void showProduct(String productId){
        UIHelp.toastMessage(productId);
    }

}
