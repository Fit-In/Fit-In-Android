package com.example.fitin_v2.ui.onboard.webview;

import android.app.Application;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class WebViewViewModel extends AndroidViewModel {

    private final String Naver = "https://nid.naver.com/nidlogin.login?mode=form&url=https%3A%2F%2Fwww.naver.com";
    private final String Google = "https://accounts.google.com/ServiceLogin/signinchooser?hl=ko&passive=true&continue=https%3A%2F%2Fwww.google.com%2F%3Fhl%3Dko&ec=GAZAmgQ&flowName=GlifWebSignIn&flowEntry=ServiceLogin";


    private MutableLiveData<String> urls;
    public LiveData<String> webUrl;



    public WebViewViewModel(String url, @NonNull Application application) {
        super(application);

        if (url.equals("KaKao")) {
            urls = new MutableLiveData<String>();
            urls.setValue(Naver);
            webUrl = urls;
        } else if (url.equals("Google")) {
            urls = new MutableLiveData<String>();
            urls.setValue(Google);
            webUrl = urls;
        }

    }


}
