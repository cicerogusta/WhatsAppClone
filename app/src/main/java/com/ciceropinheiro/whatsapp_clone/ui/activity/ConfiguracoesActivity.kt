package com.ciceropinheiro.whatsapp_clone.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import com.ciceropinheiro.whatsapp_clone.databinding.ActivityConfiguracoesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConfiguracoesActivity : BaseActivity<ConfiguracoesActivityViewModel, ActivityConfiguracoesBinding>() {
    override val viewModel: ConfiguracoesActivityViewModel by viewModels()


    override fun getViewBinding(): ActivityConfiguracoesBinding = ActivityConfiguracoesBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupToolbarActivity(binding.toolbarConfig.toolbarHome)

    }

}