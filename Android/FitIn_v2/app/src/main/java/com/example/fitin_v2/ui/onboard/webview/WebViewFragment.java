package com.example.fitin_v2.ui.onboard.webview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.fitin_v2.R;
import com.example.fitin_v2.databinding.FragmentWebViewBinding;
import com.example.fitin_v2.ui.home.HomeActivity;

public class WebViewFragment extends Fragment {

    private FragmentWebViewBinding binding;
    private WebView webView;

    private String KaKao = "http://10.0.2.2:8080/oauth2/authorize/kakao?redirect_uri=http://localhost:8080/auth/token";
    private String Google = "http://10.0.2.2:8080/oauth2/authorize/google?redirect_uri=http://localhost:8080/auth/token";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_web_view, container, false);

        final String url = WebViewFragmentArgs.fromBundle(getArguments()).getUrl();

        webView = binding.webView;


        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.setWebViewClient(new ViewClient());
        setUrl(webView, url);

        return binding.getRoot();
    }

    private void setUrl(WebView webView, String url) {
        if (url.equals("KaKao")) {
            webView.loadUrl(KaKao);
        } else if (url.equals("Google")) {
            webView.loadUrl(Google);
        }
    }

    private class ViewClient extends WebViewClient {

        // RedirectUrl로 들어올 때 값이 중요하기 때문에 해당 페이지에 따라서 boolean으로 처리해서 확인함, error가 있으면 토스트 메시지 없으면 메인으로
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            if (url.contains("error")) {
                if (isAdded()) {
                    Toast.makeText(requireActivity().getApplicationContext(), "error", Toast.LENGTH_LONG).show();
                    NavHostFragment.findNavController(WebViewFragment.this).navigate(WebViewFragmentDirections.actionWebViewFragmentToMainFragment());
                }
            } else {
                if (isAdded() && url.contains("token")) {
                    Intent intentHome = new Intent(requireActivity(), HomeActivity.class);
                    startActivity(intentHome);
                    requireActivity().overridePendingTransition(0, 0);
                    requireActivity().finish();
                    // callAPI 호출, 뉴스 데이터 불러옴
                }
            }

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }
    }
}