package com.example.my;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    WebView web;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setFullScreen();
        ActionBar bar=getActionBar();
        if(bar!=null)bar.hide();
        setContentView(R.layout.activity_main);


        web=(WebView) findViewById(R.id.web);
        WebSettings webSettings = web.getSettings();

        // 设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);

        web.addJavascriptInterface(new AndroidtoJs(this), "situ");//AndroidtoJS类对象映射到js的test对象

        if (savedInstanceState == null)
        {
            web.loadUrl("file:///android_asset/test.html");
        }
        web.setWebChromeClient(new MyWebChromeClient());
        web.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if(url.indexOf("mail.qq.com")>0) return true;
                return false;  //false   当前activity加载显示
            }


        });



    }
    public void setFullScreen()
    {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}