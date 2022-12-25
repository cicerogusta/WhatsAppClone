package com.ciceropinheiro.whatsapp_clone

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentActivity
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.ciceropinheiro.whatsapp_clone.adapter.ContatosAdapter
import com.ciceropinheiro.whatsapp_clone.adapter.RecyclerItemClickListener
import com.ciceropinheiro.whatsapp_clone.adapter.TabViewPagerAdapter
import com.ciceropinheiro.whatsapp_clone.data.model.User
import com.ciceropinheiro.whatsapp_clone.databinding.FragmentContatosBinding
import com.ciceropinheiro.whatsapp_clone.databinding.FragmentHomeBinding
import com.ciceropinheiro.whatsapp_clone.extensions.navigateTo
import com.ciceropinheiro.whatsapp_clone.ui.activity.MainActivity
import com.ciceropinheiro.whatsapp_clone.ui.base.BaseFragment
import com.ciceropinheiro.whatsapp_clone.ui.fragments.home.HomeFragmentDirections
import com.ciceropinheiro.whatsapp_clone.ui.fragments.home.HomeViewModel
import com.example.firebasewithmvvm.util.toast
import com.google.android.material.tabs.TabLayoutMediator

class ContatosFragment : BaseFragment<FragmentContatosBinding, ContatosViewModel>() {
    lateinit var mActivity: FragmentActivity
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentContatosBinding = FragmentContatosBinding.inflate(inflater, container, false)

    override val viewModel: ContatosViewModel by hiltNavGraphViewModels(R.id.nav_graph)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar()
        viewModel.retornaContatos()
        observer()

    }
    private fun observer() {
        viewModel.users.observe(viewLifecycleOwner) {
            configurarRecyclerView(it)
        }
    }

    private fun configurarRecyclerView(it: MutableList<User>) {
        binding.rvContatos.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = ContatosAdapter(it, requireContext())
        }
        binding.rvContatos.addOnItemTouchListener(RecyclerItemClickListener(activity, binding.rvContatos, object : RecyclerItemClickListener.OnItemClickListener {
            override fun onItemClick(view: View?, position: Int) {
                navigateTo(ContatosFragmentDirections.actionContatosFragmentToChatFragment())
            }

            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

            }

            override fun onLongItemClick(view: View?, position: Int) {

            }

        }))
    }


    private fun setUpToolbar() {

        val mainActivity = mActivity as MainActivity

        mainActivity.setSupportActionBar(binding.toolbarConfig.toolbarHome)
        val navController = NavHostFragment.findNavController(this)
        NavigationUI.setupActionBarWithNavController(mainActivity,navController)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.let { mActivity = it }
    }
}



