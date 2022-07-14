package com.example.fitin_kotlin.ui.onboard.socialsign

import android.content.Intent
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
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.fitin_kotlin.R
import com.example.fitin_kotlin.data.local.EncryptedSharedPreferenceController
import com.example.fitin_kotlin.databinding.FragmentSocialSignBinding
import com.example.fitin_kotlin.ui.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_social_sign.*
import javax.inject.Inject

@AndroidEntryPoint
class SocialSignFragment : Fragment() {

    @Inject
    lateinit var prefs : EncryptedSharedPreferenceController

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

        socialSign.webViewClient = ViewClinet(this, prefs)
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

    private class ViewClinet(private val fragment: SocialSignFragment, private val prefs: EncryptedSharedPreferenceController): WebViewClient() {

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            if (url!!.contains("error")) {
                if(fragment.isAdded) {
                    fragment.findNavController().navigate(SocialSignFragmentDirections.actionSocialSignFragmentToWelcomeFragment())
                }
            } else {
                if(fragment.isAdded && url.contains("refreshToken")) {
                    val urls: String = url
                    prefs.setAccessToken(urls.substring(urls.indexOf("=") + 1,urls.indexOf("&")))

                    val intentToHome = Intent(fragment.requireActivity(), HomeActivity::class.java)
                    fragment.startActivity(intentToHome)
                    fragment.requireActivity().overridePendingTransition(0, 0)
                    fragment.requireActivity().finish()

                    // 소셜 로그인에서 넘어가면 뉴스 호출이 안 뜨는데 이건 어차피 뉴스 호출 자동화 할 예정이고 홈화면은 개편할 것이므로 결국 성공한 것임
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