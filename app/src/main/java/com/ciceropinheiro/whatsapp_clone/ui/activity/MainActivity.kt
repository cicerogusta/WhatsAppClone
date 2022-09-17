package com.ciceropinheiro.whatsapp_clone.ui.activity

import android.os.Bundle
import android.text.Layout
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
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
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navHostFragment: NavHostFragment
    override val viewModel: MainActivityViewModel by viewModels()


    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(binding.toolbar.toolbarHome)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
         navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment))
        supportActionBar?.setDisplayHomeAsUpEnabled(false)


        setupActionBarWithNavController(navController, appBarConfiguration)
        setupViews()


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuSair -> {
                viewModel.deslogaUsuario()
                callFragment(HomeFragmentDirections.actionHomeFragmentToLoginFragment())

            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun callFragment(navigation: NavDirections) {
        navController.navigate(navigation)
    }

    private fun setupViews() {
        val tabLayout = binding.toolbar.tabLayout
        val viewPager = binding.toolbar.pager
        val adapter = TabViewPagerAdapter(this)
        viewPager.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager) {
            tab, position ->
            tab.text = getString(adapter.tabs[position])
        }.attach()
    }



}