package com.ciceropinheiro.whatsapp_clone

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ciceropinheiro.whatsapp_clone.adapter.ContatosAdapter
import com.ciceropinheiro.whatsapp_clone.adapter.TabViewPagerAdapter
import com.ciceropinheiro.whatsapp_clone.databinding.FragmentContatosBinding
import com.ciceropinheiro.whatsapp_clone.databinding.FragmentHomeBinding
import com.ciceropinheiro.whatsapp_clone.extensions.navigateTo
import com.ciceropinheiro.whatsapp_clone.ui.base.BaseFragment
import com.ciceropinheiro.whatsapp_clone.ui.fragments.home.HomeFragmentDirections
import com.ciceropinheiro.whatsapp_clone.ui.fragments.home.HomeViewModel
import com.example.firebasewithmvvm.util.toast
import com.google.android.material.tabs.TabLayoutMediator

class ContatosFragment : BaseFragment<FragmentContatosBinding, ContatosViewModel>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentContatosBinding = FragmentContatosBinding.inflate(inflater, container, false)

    override val viewModel: ContatosViewModel by hiltNavGraphViewModels(R.id.nav_graph)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbarConfig.toolbarHome.title = "Contatos"
        viewModel.retornaContatos()
        observer()
    }
    private fun observer() {
        viewModel.users.observe(viewLifecycleOwner) {
            binding.rvContatos.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = ContatosAdapter(it, requireContext())
            }
        }
    }




}