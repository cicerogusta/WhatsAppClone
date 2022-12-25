package com.ciceropinheiro.whatsapp_clone.ui.activity

import android.os.Bundle
import android.text.Layout
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.ciceropinheiro.whatsapp_clone.R
import com.ciceropinheiro.whatsapp_clone.adapter.TabViewPagerAdapter
import com.ciceropinheiro.whatsapp_clone.databinding.ActivityMainBinding
import com.ciceropinheiro.whatsapp_clone.ui.fragments.home.HomeFragmentDirections
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainActivityViewModel, ActivityMainBinding>() {

    private lateinit var navController: NavController
    lateinit var appBarConfiguration: AppBarConfiguration
    override val viewModel: MainActivityViewModel by viewModels()


    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        windows
        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.contatosFragment,
            R.id.homeFragment)

        )


        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment?
         navController = navHostFragment!!.navController


    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController,appBarConfiguration)
    }




}