package com.ciceropinheiro.whatsapp_clone.ui.fragments.chat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.ciceropinheiro.whatsapp_clone.R
import com.ciceropinheiro.whatsapp_clone.databinding.FragmentChatBinding
import com.ciceropinheiro.whatsapp_clone.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatFragment : BaseFragment<FragmentChatBinding, ChatFragmentViewModel>() {
    override val viewModel: ChatFragmentViewModel by hiltNavGraphViewModels(R.id.nav_graph)

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentChatBinding = FragmentChatBinding.inflate(layoutInflater)

}