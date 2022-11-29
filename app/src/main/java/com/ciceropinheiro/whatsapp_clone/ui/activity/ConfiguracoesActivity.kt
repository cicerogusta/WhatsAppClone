package com.ciceropinheiro.whatsapp_clone.ui.activity

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.ciceropinheiro.whatsapp_clone.databinding.ActivityConfiguracoesBinding
import com.ciceropinheiro.whatsapp_clone.util.Permissao
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConfiguracoesActivity : BaseActivity<ConfigViewModel, ActivityConfiguracoesBinding>() {
    private val permissoesNecessarias = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.CAMERA
    )
    override val viewModel: ConfigViewModel by viewModels()


    override fun getViewBinding(): ActivityConfiguracoesBinding = ActivityConfiguracoesBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Permissao.validarPermissoes(permissoesNecessarias, this, 1)
        val toolbar = binding.toolbarConfig.toolbarHome
        setupToolbarActivity(toolbar)


    }

}