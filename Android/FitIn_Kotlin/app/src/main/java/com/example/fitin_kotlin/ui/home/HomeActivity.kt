package com.example.fitin_kotlin.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.fitin_kotlin.R
import com.example.fitin_kotlin.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityHomeBinding = DataBindingUtil.setContentView(this,R.layout.activity_home)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcv_nav_home_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bnvMenu.setupWithNavController(navController)

    }
}