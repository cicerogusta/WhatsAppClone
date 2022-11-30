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
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.ciceropinheiro.whatsapp_clone.databinding.ActivityConfiguracoesBinding
import com.ciceropinheiro.whatsapp_clone.util.Permissao
import com.ciceropinheiro.whatsapp_clone.util.SELECA0_CAMERA
import com.ciceropinheiro.whatsapp_clone.util.SELECA0_GALERIA
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConfiguracoesActivity : BaseActivity<ConfigViewModel, ActivityConfiguracoesBinding>() {
    private val permissoesNecessarias = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.CAMERA
    )
    override val viewModel: ConfigViewModel by viewModels()


    override fun getViewBinding(): ActivityConfiguracoesBinding = ActivityConfiguracoesBinding.inflate(layoutInflater)

    @SuppressLint("QueryPermissionsNeeded")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Permissao.validarPermissoes(permissoesNecessarias, this, 1)
        val toolbar = binding.toolbarConfig.toolbarHome
        setupToolbarActivity(toolbar)

        binding.ibcamera.setOnClickListener {
            val i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (i.resolveActivity(packageManager) != null) {
                startActivityForResult(i, SELECA0_CAMERA)
            }

        }

        binding.ibgaleria.setOnClickListener {
            val i = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            if (i.resolveActivity(packageManager) != null) {
                startActivityForResult(i, SELECA0_GALERIA)
            }

        }


    }

    @Deprecated("Deprecated in Java",
        ReplaceWith("super.onActivityResult(requestCode, resultCode, data)")
    )
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            var imagemBtm: Bitmap? = null
            try {
                when(requestCode) {
                    SELECA0_CAMERA -> {
                        imagemBtm = data?.extras?.get("data") as Bitmap

                    }
                    SELECA0_GALERIA -> {
                           val localImagemSelecionada = data?.data
                        imagemBtm = MediaStore.Images.Media.getBitmap(contentResolver, localImagemSelecionada)

                    }
                }
                if (imagemBtm != null) {
                    binding.profileImage.setImageBitmap(imagemBtm)
                }
            }catch (e: Exception) {
                e.printStackTrace()

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