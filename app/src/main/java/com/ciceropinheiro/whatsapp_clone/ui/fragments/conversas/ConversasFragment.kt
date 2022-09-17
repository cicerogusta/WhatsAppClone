package com.ciceropinheiro.whatsapp_clone.ui.fragments.conversas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.ciceropinheiro.whatsapp_clone.R
import com.ciceropinheiro.whatsapp_clone.databinding.FragmentConversasBinding
import com.ciceropinheiro.whatsapp_clone.ui.base.BaseFragment


class ConversasFragment : BaseFragment<FragmentConversasBinding, ChamadasViewModel>() {

    override val viewModel: ChamadasViewModel by hiltNavGraphViewModels(R.id.nav_graph)

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentConversasBinding = FragmentConversasBinding.inflate(inflater, container, false)
}