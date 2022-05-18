package com.example.fitin_v2.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.fitin_v2.R;
import com.example.fitin_v2.databinding.ActivityHomeBinding;
import com.example.fitin_v2.databinding.ActivityMainBinding;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;
    private HomeViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding.setLifecycleOwner(this);
        binding.setHomeViewModel(viewModel);


    }
}