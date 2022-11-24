package com.example.fitin_kotlin.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.fitin_kotlin.R
import com.example.fitin_kotlin.databinding.ActivityHomeBinding
import com.example.fitin_kotlin.ui.bookmark.BookmarkViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_home.*

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityHomeBinding = DataBindingUtil.setContentView(this,R.layout.activity_home)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcv_nav_home_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bnvMenu.setupWithNavController(navController)

        navController.addOnDestinationChangedListener{ controller, destination, arguments ->
            when (destination.id) {
                R.id.newsDetailFragment -> bnv_menu.visibility = View.GONE
                R.id.recruitmentDetailFragment -> bnv_menu.visibility = View.GONE
                R.id.addBookmarkFragment -> bnv_menu.visibility = View.GONE
                R.id.createBookmarkFragment -> bnv_menu.visibility = View.GONE
                R.id.myNewsFragment -> bnv_menu.visibility = View.GONE
                R.id.myRecruitmentFragment -> bnv_menu.visibility = View.GONE
                R.id.bookmarkMyNewsFragment -> bnv_menu.visibility = View.GONE
                R.id.bookmarkMyRecruitmentFragment -> bnv_menu.visibility = View.GONE
                else -> bnv_menu.visibility = View.VISIBLE
            }
        }

    }
}