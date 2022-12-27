package com.ciceropinheiro.whatsapp_clone.ui.fragments.chat

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import com.bumptech.glide.Glide
import com.ciceropinheiro.whatsapp_clone.R
import com.ciceropinheiro.whatsapp_clone.databinding.FragmentChatBinding
import com.ciceropinheiro.whatsapp_clone.ui.activity.MainActivity
import com.ciceropinheiro.whatsapp_clone.ui.base.BaseFragment
import com.example.firebasewithmvvm.util.toast
import dagger.hilt.android.AndroidEntryPoint


class ChatFragment : BaseFragment<FragmentChatBinding, ChatFragmentViewModel>() {
    override val viewModel: ChatFragmentViewModel by hiltNavGraphViewModels(R.id.nav_graph)
    lateinit var mActivity: FragmentActivity
    private val args : ChatFragmentArgs by navArgs()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentChatBinding = FragmentChatBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let { mActivity = it }
        setUpToolbar()
        if (!args.user.equals(null)) binding.user = args.user
        if (!args.user.foto.equals(null)) {

            Glide.with(this).load(args.user.foto).into(binding.circleIv)
        } else {
            binding.circleIv.setImageResource(R.drawable.padrao)
            toast(args.user.toString())
        }

    }

    private fun setUpToolbar() {

        val mainActivity = mActivity as MainActivity

        binding.toolbar.title = ""

        mainActivity.setSupportActionBar(binding.toolbar)
        val navController = NavHostFragment.findNavController(this)
        NavigationUI.setupActionBarWithNavController(mainActivity,navController)

    }
}