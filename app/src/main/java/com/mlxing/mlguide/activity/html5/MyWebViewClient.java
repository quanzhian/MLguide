package com.mlxing.mlguide.activity.html5;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.Browser;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.mlxing.mlguide.application.AppApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quan on 2016/6/19.
 */
public class MyWebViewClient  extends WebViewClient {

    private Context context;
    private String filterUrlHeader;
    private ProgressBar progressView;

    public MyWebViewClient(Context context , ProgressBar progressView,String filterUrlHeader){
        this.context = context;
        this.filterUrlHeader = filterUrlHeader;
        this.progressView = progressView;
    }

    /**
     * 加载过程中 拦截加载的地址url
     *
     * @param view
     * @param url  被拦截的url
     * @return
     */
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        Log.i("zttjiangqq", "-------->shouldOverrideUrlLoading url:" + url);
        //这边因为考虑到之前项目的问题，这边拦截的url过滤掉了zttmall://开头的地址
        //在其他项目中 大家可以根据实际情况选择不拦截任何地址，或者有选择性拦截
        if (filterUrlHeader != null && !url.startsWith(filterUrlHeader)) {
            Uri mUri = Uri.parse(url);
            List<String> browerList = new ArrayList<String>();
            browerList.add("http");
            browerList.add("https");
            browerList.add("about");
            browerList.add("javascript");
            if (browerList.contains(mUri.getScheme())) {
                return false;
            } else {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                //如果另外的应用程序WebView，我们可以进行重用
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(Browser.EXTRA_APPLICATION_ID, AppApplication.context.getPackageName());
                try {
                    AppApplication.context.startActivity(intent);
                    return true;
                } catch (ActivityNotFoundException ex) {}
            }
            return false;
        } else {
            return true;
        }
    }

    /**
     * 页面加载过程中，加载资源回调的方法
     *
     * @param view
     * @param url
     */
    @Override
    public void onLoadResource(WebView view, String url) {
        super.onLoadResource(view, url);
        Log.i(this.getClass().getSimpleName(), "-------->onLoadResource url:" + url);
    }

    /**
     * 页面加载完成回调的方法
     *
     * @param view
     * @param url
     */
    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        // 关闭图片加载阻塞
        view.getSettings().setBlockNetworkImage(false);
        progressView.setVisibility(View.GONE);
    }

    /**
     * 页面开始加载调用的方法
     *
     * @param view
     * @param url
     * @param favicon
     */
    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        Log.d(this.getClass().getSimpleName(), "onPageStarted:-----------" + url);
        super.onPageStarted(view, url, favicon);
    }

    @Override
    public void onReceivedError(WebView view, int errorCode,
                                String description, String failingUrl) {
        super.onReceivedError(view, errorCode, description, failingUrl);
    }

    @Override
    public void onScaleChanged(WebView view, float oldScale, float newScale) {
        super.onScaleChanged(view, oldScale, newScale);
    }

}
