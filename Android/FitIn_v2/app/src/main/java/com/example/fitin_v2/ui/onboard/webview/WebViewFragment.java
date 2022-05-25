package com.example.fitin_v2.ui.onboard.webview;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.fitin_v2.R;
import com.example.fitin_v2.databinding.FragmentWebViewBinding;
import com.example.fitin_v2.ui.onboard.main.MainActivity;

public class WebViewFragment extends Fragment {

    private WebViewViewModel viewModel;
    private FragmentWebViewBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_web_view, container, false);

        final WebViewViewModelFactory viewModelFactory = new WebViewViewModelFactory(WebViewFragmentArgs.fromBundle(getArguments()).getUrl(),requireActivity().getApplication());

        viewModel = new ViewModelProvider(this, viewModelFactory).get(WebViewViewModel.class);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setWebviewViewModel(viewModel);
        
        return binding.getRoot();
    }
}