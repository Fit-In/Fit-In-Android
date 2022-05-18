package com.example.fitin_v2.ui.onboard.signup;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fitin_v2.R;
import com.example.fitin_v2.databinding.FragmentSignUpFirstBinding;


public class SignUpFirstFragment extends Fragment {

    private FragmentSignUpFirstBinding binding;
    private SignUpFirstViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up_first, container, false);
        // 동일하게 ViewModel을 받아와서 navController에 argument로 넘겨줌
        viewModel = new ViewModelProvider(this).get(SignUpFirstViewModel.class);

        binding.setLifecycleOwner(this);

        viewModel.getBack().observe(getViewLifecycleOwner(), back -> {
            if(back) {
                NavHostFragment.findNavController(SignUpFirstFragment.this).navigate(SignUpFirstFragmentDirections.actionSignUpFirstFragmentToMainFragment());
                viewModel.onBackComplete();
            }
        });

        // 회원가입 두번째 페이지로 가게끔 true/false 구분하고 navigation 처리와 함꼐 argument 처리
        viewModel.getAccount().observe(getViewLifecycleOwner(), Account -> {
            if (Account != null) {
                NavHostFragment.findNavController(SignUpFirstFragment.this).navigate(SignUpFirstFragmentDirections.actionSignUpFirstFragmentToSignUpSecondFragment(Account));
                viewModel.getEventSignUpComplete();
            }
        });

        binding.setSignUpFirstViewModel(viewModel);

        return binding.getRoot();
    }
}