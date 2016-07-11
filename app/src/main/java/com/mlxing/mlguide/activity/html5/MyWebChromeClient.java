package com.mlxing.mlguide.activity.html5;

import android.util.Log;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by quan on 2016/6/19.
 */
public class MyWebChromeClient extends WebChromeClient {

    private ProgressBar progressView;

    private TextView titleView;

    public MyWebChromeClient(TextView titleView,ProgressBar progressView){
        this.titleView = titleView;
        this.progressView = progressView;
    }

    /**
     * 网页加载标题回调
     *
     * @param view
     * @param title
     */
    @Override
    public void onReceivedTitle(WebView view, String title) {
        Log.d(this.getClass().getSimpleName(), "当前网页标题为:" + title);
        titleView.setText(title);
    }

    /**
     * 网页加载进度回调
     *
     * @param view
     * @param newProgress
     */
    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        // 设置进行进度
        progressView.setVisibility(View.VISIBLE);
        progressView.setProgress(newProgress);
        //progressView.postInvalidate(); // 刷新UI
        Log.d(this.getClass().getSimpleName(), "进度为:" + newProgress);
    }

    @Override
    public boolean onJsAlert(WebView view, String url, String message,
                             JsResult result) {
        return super.onJsAlert(view, url, message, result);

    }
}

