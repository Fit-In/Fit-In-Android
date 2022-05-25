package com.example.fitin_v2.util;

import android.webkit.WebView;

import androidx.databinding.BindingAdapter;

@SuppressWarnings("unused")
public class BindingAdapters {

    @BindingAdapter("loadUrl")
    public static void loadUrl(WebView webView, String url) {
        webView.loadUrl(url);
    }
}
