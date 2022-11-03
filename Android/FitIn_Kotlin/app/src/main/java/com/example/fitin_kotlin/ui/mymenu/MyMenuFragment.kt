package com.example.fitin_kotlin.ui.mymenu

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.fitin_kotlin.R
import com.example.fitin_kotlin.databinding.FragmentMyMenuBinding
import com.example.fitin_kotlin.ui.home.HomeActivity
import com.example.fitin_kotlin.ui.onboard.welcome.WelcomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyMenuFragment : Fragment() {

    private val myMenuViewModel: MyMenuViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentMyMenuBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_menu, container, false)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.myMenuViewModel = myMenuViewModel

        myMenuViewModel.eventTos.observe(viewLifecycleOwner, Observer { tos ->
            if (tos) {
                findNavController().navigate(MyMenuFragmentDirections.actionMyMenuFragmentToTosFragment())
                myMenuViewModel.onEventTosComplete()
            }
        })

        myMenuViewModel.eventSignOut.observe(viewLifecycleOwner, Observer { signOut ->
            if (signOut) {
                val intentToWelcome = Intent(activity, WelcomeActivity::class.java)
                startActivity(intentToWelcome)
                activity?.overridePendingTransition(0,0)
                activity?.finish()
                myMenuViewModel.onSignOutComplete()
            }
        })

        myMenuViewModel.eventBack.observe(viewLifecycleOwner, Observer { back ->
            if (back) {
                findNavController().popBackStack()
                myMenuViewModel.onBackComplete()
            }
        })


        return binding.root
    }

}