package com.ciceropinheiro.whatsapp_clone.ui.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.ciceropinheiro.whatsapp_clone.R
import com.ciceropinheiro.whatsapp_clone.databinding.ActivityConfiguracoesBinding
import com.ciceropinheiro.whatsapp_clone.util.Permissao
import com.ciceropinheiro.whatsapp_clone.util.SELECA0_CAMERA
import com.ciceropinheiro.whatsapp_clone.util.SELECA0_GALERIA
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConfiguracoesActivity : BaseActivity<ConfiguracoesActivityViewModel, ActivityConfiguracoesBinding>() {
    private val permissoesNecessarias = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.CAMERA
    )
    private val register = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        if (uri != null) {
            viewModel.salvarImagem(this,uri)
        } else {
            // insert code for toast showing no media selected
        }
    }
    override val viewModel: ConfiguracoesActivityViewModel by viewModels()


    override fun getViewBinding(): ActivityConfiguracoesBinding = ActivityConfiguracoesBinding.inflate(layoutInflater)

    @SuppressLint("QueryPermissionsNeeded")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Permissao.validarPermissoes(permissoesNecessarias, this, 1)
        val toolbar = binding.toolbarConfig.toolbarHome
//        setupToolbarActivity(toolbar)

        val uri = viewModel.pegaPerfilUsuario(this)
        if (uri !=null) {
            Glide.with(this).load(uri.toString()).into(binding.profileImage)


        } else {
            binding.profileImage.setImageResource(R.drawable.padrao)
            Toast.makeText(this, uri, Toast.LENGTH_SHORT).show()

        }

        binding.ibcamera.setOnClickListener {

            register.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))

        }

        binding.ibgaleria.setOnClickListener {
            val i = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            if (i.resolveActivity(packageManager) != null) {
                startActivityForResult(i, SELECA0_GALERIA)
            }

        }


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