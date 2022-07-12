package com.example.fitin_kotlin.ui.onboard.socialsign

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.fitin_kotlin.R
import com.example.fitin_kotlin.databinding.FragmentSocialSignBinding
import kotlinx.android.synthetic.main.fragment_social_sign.*


class SocialSignFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentSocialSignBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_social_sign, container, false)

        val url = SocialSignFragmentArgs.fromBundle(requireArguments()).url

        val socialSign: WebView = binding.webView
        val webSettings: WebSettings = socialSign.settings
        webSettings.javaScriptEnabled = true

        socialSign.webViewClient = ViewClinet(this)
        setUrl(socialSign, url)

        return binding.root
    }

    private fun setUrl(webView: WebView, url: String) {
        if (url.equals("KaKao")) {
            webView.loadUrl(Kakao)
        } else if (url.equals("Naver")) {
            webView.loadUrl(Naver)
        }
    }

    private class ViewClinet(private val fragment: SocialSignFragment): WebViewClient() {
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            if (url!!.contains("error")) {
                if(fragment.isAdded) {
                    Toast.makeText(fragment.requireActivity().applicationContext, "error", Toast.LENGTH_SHORT).show()
                    fragment.findNavController().navigate(SocialSignFragmentDirections.actionSocialSignFragmentToWelcomeFragment())
                }
            } else {
                if(fragment.isAdded && url.contains("token")) {
                    // 인텐트 처리(홈화면으로 바로 넘어감) + 이 부분에서 url에서 온 token 이후 값을 저장함(이게 access token)
                    Toast.makeText(fragment.requireActivity().applicationContext, "로그인 성공", Toast.LENGTH_SHORT).show()
                    Log.e("token", url)
                }
            }
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
        }
    }

    companion object {
        private const val Kakao = "http://10.0.2.2:8080/oauth2/authorize/kakao?redirect_uri=http://localhost:8080/auth/token"
        private const val Naver = "http://10.0.2.2:8080/oauth2/authorize/naver?redirect_uri=http://localhost:8080/auth/token"
    }


}