package com.ciceropinheiro.whatsapp_clone.ui.fragments.home

import android.os.Bundle
import android.view.*
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.ciceropinheiro.whatsapp_clone.R
import com.ciceropinheiro.whatsapp_clone.adapter.TabViewPagerAdapter
import com.ciceropinheiro.whatsapp_clone.databinding.FragmentHomeBinding
import com.ciceropinheiro.whatsapp_clone.ui.base.BaseFragment
import com.example.firebasewithmvvm.util.toast
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)

    override val viewModel: HomeViewModel by hiltNavGraphViewModels(R.id.nav_graph)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        binding.toolbar.toolbarHome.title = "WhatsApp"
        binding.toolbar.toolbarHome.inflateMenu(R.menu.menu_main)
        binding.toolbar.toolbarHome.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.menuSair -> {
                    viewModel.deslogaUsuario()
                    navigateTo(HomeFragmentDirections.actionHomeFragmentToLoginFragment())

                }
                R.id.menuConfig -> {
                    navigateTo(HomeFragmentDirections.actionHomeFragmentToConfigActivity())
                }
            }
            true
        }

    }

    private fun setupViews() {
        val tabLayout = binding.tabLayout
        val viewPager = binding.pager
        val adapter = TabViewPagerAdapter(this)
        viewPager.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager) {
                tab, position ->
            tab.text = getString(adapter.tabs[position])
        }.attach()
    }

    fun navigateTo(navigation: NavDirections) {
        findNavController().navigate(navigation)
    }




}