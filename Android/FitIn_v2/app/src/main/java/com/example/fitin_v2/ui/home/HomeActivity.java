package com.example.fitin_v2.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.fitin_v2.R;
import com.example.fitin_v2.databinding.ActivityHomeBinding;


public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityHomeBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
    }
}