package com.ciceropinheiro.whatsapp_clone.ui.activity

import android.Manifest
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        for (premissaoResultado in grantResults) {
            if (premissaoResultado == PackageManager.PERMISSION_DENIED) {
                alertaValidacaoPermissao()
            }
        }
    }

    private fun alertaValidacaoPermissao() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Permissões Negadas")
        builder.setMessage("Para utilizar o app é necessário aceitar as permissões")
        builder.setCancelable(false)
        builder.setPositiveButton("Confirmar", DialogInterface.OnClickListener { dialogInterface, i ->
            finish()
        })

        val dialog = builder.create()
        dialog.show()
    }

}