package com.ciceropinheiro.whatsapp_clone.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import com.ciceropinheiro.whatsapp_clone.R
import com.ciceropinheiro.whatsapp_clone.databinding.ActivityConfiguracoesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConfiguracoesActivity : BaseActivity<ConfiguracoesActivityViewModel, ActivityConfiguracoesBinding>() {
    override val viewModel: ConfiguracoesActivityViewModel by viewModels()


    override fun getViewBinding(): ActivityConfiguracoesBinding = ActivityConfiguracoesBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupToolbar(binding.toolbarConfig.toolbarHome)

    }

}