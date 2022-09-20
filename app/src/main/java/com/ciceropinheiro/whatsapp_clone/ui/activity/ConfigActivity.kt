package com.ciceropinheiro.whatsapp_clone.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import com.ciceropinheiro.whatsapp_clone.databinding.ActivityConfigBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConfigActivity : BaseActivity<ConfigViewModel, ActivityConfigBinding>() {
    override val viewModel: ConfigViewModel by viewModels()


    override fun getViewBinding(): ActivityConfigBinding = ActivityConfigBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val toolbar = binding.toolbarConfig.toolbarHome
        toolbar.title = "Configurações"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

}