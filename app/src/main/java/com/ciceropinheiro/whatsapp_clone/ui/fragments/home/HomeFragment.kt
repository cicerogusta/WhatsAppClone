package com.ciceropinheiro.whatsapp_clone.ui.fragments.home

import android.view.*
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.ciceropinheiro.whatsapp_clone.R
import com.ciceropinheiro.whatsapp_clone.adapter.TabViewPagerAdapter
import com.ciceropinheiro.whatsapp_clone.databinding.FragmentHomeBinding
import com.ciceropinheiro.whatsapp_clone.ui.base.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)

    override val viewModel: HomeViewModel by hiltNavGraphViewModels(R.id.nav_graph)




}