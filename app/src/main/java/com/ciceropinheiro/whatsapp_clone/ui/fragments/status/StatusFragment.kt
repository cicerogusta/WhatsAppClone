package com.ciceropinheiro.whatsapp_clone.ui.fragments.status

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.ciceropinheiro.whatsapp_clone.R
import com.ciceropinheiro.whatsapp_clone.databinding.FragmentStatusBinding
import com.ciceropinheiro.whatsapp_clone.ui.base.BaseFragment
import com.ciceropinheiro.whatsapp_clone.ui.fragments.conversas.ChamadasViewModel

class StatusFragment : BaseFragment<FragmentStatusBinding, StatusViewModel>() {

    override val viewModel: StatusViewModel by hiltNavGraphViewModels(R.id.nav_graph)

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentStatusBinding = FragmentStatusBinding.inflate(inflater, container, false)
}