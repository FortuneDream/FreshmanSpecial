package com.excitingboat.freshmanspecial.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.excitingboat.freshmanspecial.R;

public class WebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        WebView webView = (WebView) findViewById(R.id.web_view);
        webView.loadUrl(getIntent().getStringExtra("url"));
    }
}
