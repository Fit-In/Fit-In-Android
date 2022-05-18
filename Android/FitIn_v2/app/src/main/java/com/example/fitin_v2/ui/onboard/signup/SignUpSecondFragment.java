package com.example.fitin_v2.ui.onboard.signup;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fitin_v2.R;
import com.example.fitin_v2.databinding.FragmentSignUpSecondBinding;
import com.example.fitin_v2.model.AccountRequestDto;


public class SignUpSecondFragment extends Fragment {

    private SignUpSecondViewModel viewModel;

    private FragmentSignUpSecondBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up_second, container, false);
        // SignUpFirst에서 넘겨받은 값, ViewModelFactory로 같이 만듬
        final AccountRequestDto accounts = SignUpSecondFragmentArgs.fromBundle(getArguments()).getAccountRequestDto();
        final SignUpSecondViewModelFactory viewModelFactory = new SignUpSecondViewModelFactory(accounts, requireActivity().getApplication());

        viewModel = new ViewModelProvider(this, viewModelFactory).get(SignUpSecondViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setSignUpSecondViewModel(viewModel);

        viewModel.getBack().observe(getViewLifecycleOwner(), back -> {
            if (back) {
                NavHostFragment.findNavController(SignUpSecondFragment.this).navigate(SignUpSecondFragmentDirections.actionSignUpSecondFragmentToSignUpFirstFragment());
                viewModel.onBackComplete();
            }
        });


        return binding.getRoot();
    }
}