package com.example.fitin_v2.ui.onboard.signin;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fitin_v2.R;
import com.example.fitin_v2.databinding.FragmentSignInBinding;
import com.example.fitin_v2.ui.home.HomeActivity;

public class SignInFragment extends Fragment {

    private FragmentSignInBinding binding;
    private SignInViewModel viewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in, container, false);

        viewModel = new ViewModelProvider(this).get(SignInViewModel.class);

        binding.setLifecycleOwner(this);
        binding.setSigninViewModel(viewModel);

        viewModel.getBack().observe(getViewLifecycleOwner(), back -> {
            if(back) {
                NavHostFragment.findNavController(SignInFragment.this).navigate(SignInFragmentDirections.actionSignInFragmentToMainFragment());
                viewModel.onBackComplete();
            }
        });

        viewModel.getEventSignIn().observe(getViewLifecycleOwner(), signIn -> {
            if (signIn) {
                // ViewModel에서 이미 서버와 통신했기 때문에 Intent처리는 ViewModel에서 하지 않음 Intent 처리를 ViewModel에서 하는 건 MVVM을 위배하는 상황임
                Intent intentHome = new Intent(getActivity(), HomeActivity.class);
                startActivity(intentHome);
                getActivity().overridePendingTransition(0,0);
                getActivity().finish();
                viewModel.onEventSignInComplete();
            }
//            else {
//                Toast.makeText(requireActivity().getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
//            }
        });


        // getUser를 통해서 로그인 여부 체크 mutableLiveData 없으면 토스트 메시지로 로그인 못하게 막음


        return binding.getRoot();
    }
}