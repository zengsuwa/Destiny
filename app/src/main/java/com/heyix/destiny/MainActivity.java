package com.heyix.destiny;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

public class MainActivity extends Activity {
    private WebView webview;
    private ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webview = (WebView) findViewById(R.id.webview);
        bar = (ProgressBar)findViewById(R.id.myProgressBar);
        WebSettings webset = webview.getSettings();
        //设置WebView属性，能够执行Javascript脚本
        webset.setJavaScriptEnabled(true);
        // 设置可以支持缩放
        webset.setSupportZoom(true);
        // 设置出现缩放工具
        webset.setBuiltInZoomControls(true);
        //不显示缩小镜和放大镜按钮
        webset.setDisplayZoomControls(false);
        //扩大比例的缩放
        webset.setUseWideViewPort(true);
        //自适应屏幕
        webset.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webset.setLoadWithOverviewMode(true);
        //显示进度条
        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    bar.setVisibility(View.GONE);
                } else {
                    if (View.GONE == bar.getVisibility()) {
                        bar.setVisibility(View.VISIBLE);
                    }
                    bar.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }

        });
        //加载需要显示的网页
        webview.loadUrl("http://www.heyix.com");
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
//            //goBack()表示返回WebView的上一页面
//            webview.goBack();
//            return true;
//        }else{
//
//        }
//        return false;
//    }

    @Override
    public void onBackPressed() {
        if (webview.canGoBack()) {
            //goBack()表示返回WebView的上一页面
            webview.goBack();
        }else{
            super.onBackPressed();
        }
    }
}
