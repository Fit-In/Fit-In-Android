package com.example.fitin_v1;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class WebviewActivity extends AppCompatActivity {
    String Naver = "https://nid.naver.com/nidlogin.login?mode=form&url=https%3A%2F%2Fwww.naver.com";
    String Google = "https://accounts.google.com/ServiceLogin/signinchooser?hl=ko&passive=true&continue=https%3A%2F%2Fwww.google.com%2F%3Fhl%3Dko&ec=GAZAmgQ&flowName=GlifWebSignIn&flowEntry=ServiceLogin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        setTitle("연동로그인");


        Intent inIntent = getIntent();
        final int uri= inIntent.getIntExtra("Uri",0);
        WebView webView = findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.setWebViewClient(new ViewClient());
        if(uri == 0){
            webView.loadUrl(Naver);
        }
        else if(uri == 1){
            webView.loadUrl(Google);
        }

    }

    private class ViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(final WebView view, final String url){
            view.loadUrl(url);
            return true;
        }
    }
}
