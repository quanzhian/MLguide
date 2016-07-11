package com.mlxing.mlguide.activity.html5;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mlxing.mlguide.R;
import com.mlxing.mlguide.activity.BaseActivity;
import com.mlxing.mlguide.utils.NetWorkUtils;
import com.mlxing.mlguide.utils.PreferencesUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by quan on 2016/6/18.
 */
public class WebViewActivity extends BaseActivity {

    @BindView(R.id.title)
    TextView titleView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.webview_progress)
    ProgressBar webviewProgress;
    @BindView(R.id.webview)
    WebView mWebView;

    private String mMlxJavaInterfaceName = "mlx";
    private String ad_url;
    private String title;

    public static final String PARAM_URL = "web_url";
    public static final String PARAM_TITLE = "web_title";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_layout);
        ButterKnife.bind(this);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ad_url = getIntent().getStringExtra(PARAM_URL);
        title = getIntent().getStringExtra(PARAM_TITLE);
        init();
        mWebView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent,
                                        String contentDisposition, String mimetype,
                                        long contentLength) {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        titleView.setText(title);
        //准备javascript注入
        mWebView.addJavascriptInterface(new JavaInterFaceImpl(this), mMlxJavaInterfaceName);
        if (savedInstanceState != null) {
            mWebView.restoreState(savedInstanceState);
        } else {
            if (ad_url != null) {
                mWebView.loadUrl(ad_url);
            }
        }
    }

    private void init() {
        mWebView.setWebChromeClient(new MyWebChromeClient(titleView, webviewProgress));
        mWebView.setWebViewClient(new MyWebViewClient(this, webviewProgress, ""));
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);  //开启javascript
        webSettings.setDomStorageEnabled(true);  //开启DOM
        webSettings.setDefaultTextEncodingName("utf-8"); //设置编码
        // // web页面处理
        webSettings.setAllowFileAccess(true);// 支持文件流
        // webSettings.setSupportZoom(true);// 支持缩放
        // webSettings.setBuiltInZoomControls(true);// 支持缩放
        webSettings.setUseWideViewPort(true);// 调整到适合webview大小
        webSettings.setLoadWithOverviewMode(true);// 调整到适合webview大小
        webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);// 屏幕自适应网页,如果没有这个，在低分辨率的手机上显示可能会异常
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        //提高网页加载速度，暂时阻塞图片加载，然后网页加载好了，在进行加载图片
        webSettings.setBlockNetworkImage(true);
        //开启缓存机制
        webSettings.setAppCacheEnabled(true);
        //根据当前网页连接状态
        if (NetWorkUtils.getNetworkTypeName(this).equals(NetWorkUtils.NETWORK_TYPE_WIFI)) {
            //设置无缓存
            webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        } else {
            //设置缓存
            webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mWebView != null) {
            mWebView.saveState(outState);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mWebView != null) {
            mWebView.onResume();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mWebView != null) {
            mWebView.stopLoading();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mWebView != null) {
            mWebView.onPause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.destroy();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebView.canGoBack()) {
                mWebView.goBack();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        if (mWebView != null) {
            if (mWebView.canGoBack()) {
                mWebView.goBack();
            } else {
                finish();
            }
        }
        super.onBackPressed();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_one, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.action_refresh:
                mWebView.loadUrl(ad_url);
                return true;
            case R.id.action_home:
                finish();
                return true;
            case R.id.action_collect:
                String m_key = "hsCollect_key";
                int _resId = PreferencesUtils.getInt(this,m_key);
                if(_resId == -1 || _resId == R.drawable.ic_grade_white){
                    item.setIcon(R.drawable.ic_grade_red);
                }else{
                    item.setIcon(R.drawable.ic_grade_white);
                    PreferencesUtils.putInt(this,m_key,R.drawable.ic_grade_white);
                }
                return true;
            default:break;
        }

        return super.onOptionsItemSelected(item);
    }

}
