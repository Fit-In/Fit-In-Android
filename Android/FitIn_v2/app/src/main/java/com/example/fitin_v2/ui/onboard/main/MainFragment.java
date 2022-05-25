package com.example.fitin_v2.ui.onboard.main;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fitin_v2.R;
import com.example.fitin_v2.databinding.FragmentMainBinding;
import com.example.fitin_v2.ui.onboard.signup.SignUpFirstFragment;

public class MainFragment extends Fragment {

    private FragmentMainBinding binding;
    private MainViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        binding.setLifecycleOwner(getViewLifecycleOwner());
        viewModel.getEventSignUp().observe(getViewLifecycleOwner(), eventSignUp -> {
            if (eventSignUp) {
                NavHostFragment.findNavController(MainFragment.this).navigate(MainFragmentDirections.actionMainFragmentToSignUpFirstFragment());
                viewModel.onEventSignUpComplete();
            }
        });

        viewModel.getEventSignIn().observe(getViewLifecycleOwner(), eventSignIn -> {
            if (eventSignIn) {
                NavHostFragment.findNavController(MainFragment.this).navigate(MainFragmentDirections.actionMainFragmentToSignInFragment());
                viewModel.onEventSignInComplete();
            }
        });

        viewModel.getGoogle().observe(getViewLifecycleOwner(), google -> {
            if(google != null) {
                NavHostFragment.findNavController(MainFragment.this).navigate(MainFragmentDirections.actionMainFragmentToWebViewFragment(google));
                viewModel.onGoogleComplete();
            }
        });

        viewModel.getKakao().observe(getViewLifecycleOwner(), kakao -> {
            if(kakao != null) {
                NavHostFragment.findNavController(MainFragment.this).navigate(MainFragmentDirections.actionMainFragmentToWebViewFragment(kakao));
                viewModel.onKakaoComplete();
            }
        });

        binding.setMainViewModel(viewModel);

        return binding.getRoot();
    }
}