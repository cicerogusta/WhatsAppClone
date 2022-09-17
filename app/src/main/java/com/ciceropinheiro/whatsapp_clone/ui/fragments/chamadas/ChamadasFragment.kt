package com.ciceropinheiro.whatsapp_clone.ui.fragments.chamadas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.ciceropinheiro.whatsapp_clone.R
import com.ciceropinheiro.whatsapp_clone.databinding.FragmentChamadasBinding
import com.ciceropinheiro.whatsapp_clone.ui.base.BaseFragment
import com.ciceropinheiro.whatsapp_clone.ui.fragments.conversas.ChamadasViewModel

class ChamadasFragment : BaseFragment<FragmentChamadasBinding, ChamadasViewModel>() {

    override val viewModel: ChamadasViewModel by hiltNavGraphViewModels(R.id.nav_graph)

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentChamadasBinding = FragmentChamadasBinding.inflate(inflater, container, false)
}