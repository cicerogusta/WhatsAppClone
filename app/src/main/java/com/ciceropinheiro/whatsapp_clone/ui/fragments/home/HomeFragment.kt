package com.ciceropinheiro.whatsapp_clone.ui.fragments.home

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentActivity
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.ciceropinheiro.whatsapp_clone.R
import com.ciceropinheiro.whatsapp_clone.adapter.TabViewPagerAdapter
import com.ciceropinheiro.whatsapp_clone.databinding.FragmentHomeBinding
import com.ciceropinheiro.whatsapp_clone.extensions.navigateTo
import com.ciceropinheiro.whatsapp_clone.ui.activity.MainActivity
import com.ciceropinheiro.whatsapp_clone.ui.base.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)

    lateinit var mActivity: FragmentActivity
    override val viewModel: HomeViewModel by hiltNavGraphViewModels(R.id.nav_graph)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarConfig.toolbarHome.title ="WhatsApp"
        setupMenuToolbar(binding.toolbarConfig.toolbarHome)
//        setUpToolbar()
        setupViews()
        binding.floatingActionButton4.setOnClickListener {
            navigateTo(HomeFragmentDirections.actionHomeFragmentToContatosFragment())
        }
    }

    private fun setupMenuToolbar(toolbar: Toolbar) {
        toolbar.inflateMenu(R.menu.menu_main)
        toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menuSair -> {
                    viewModel.deslogaUsuario()
                    navigateTo(HomeFragmentDirections.actionHomeFragmentToLoginFragment())
                }
                R.id.menuConfig -> {
                    navigateTo(HomeFragmentDirections.actionHomeFragmentToConfiguracoesActivity())
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
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = getString(adapter.tabs[position])
        }.attach()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.let { mActivity = it }
    }


}