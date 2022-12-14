package com.ciceropinheiro.whatsapp_clone.ui.activity

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.ciceropinheiro.whatsapp_clone.R
import com.ciceropinheiro.whatsapp_clone.data.model.User
import com.ciceropinheiro.whatsapp_clone.databinding.ActivityConfiguracoesBinding
import com.ciceropinheiro.whatsapp_clone.util.Permissao
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConfiguracoesActivity : BaseActivity<ConfiguracoesActivityViewModel, ActivityConfiguracoesBinding>() {
    private var uri: Uri? = null
    private val permissoesNecessarias = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.CAMERA
    )
    private val gallery = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        if (uri != null) {
            viewModel.salvarImagemGaleria(this,uri)
            binding.profileImage.setImageURI(uri)
        } else {

            binding.profileImage.setImageResource(R.drawable.padrao)
        }
    }


    private val camera =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                if (result?.data != null) {
                    val bitmap = result.data?.extras?.get("data") as Bitmap
                    viewModel.salvarImagemCamera(this, bitmap)
                    binding.profileImage.setImageBitmap(bitmap)
                }
            }
        }

    private var user : User = User()
    override val viewModel: ConfiguracoesActivityViewModel by viewModels()


    override fun getViewBinding(): ActivityConfiguracoesBinding = ActivityConfiguracoesBinding.inflate(layoutInflater)

    @SuppressLint("QueryPermissionsNeeded")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Permissao.validarPermissoes(permissoesNecessarias, this, 1)
        observer()
        val toolbar = binding.toolbarConfig.toolbarHome
        setupToolbarActivity(toolbar)
        viewModel.pegaPerfilUsuario()

         uri = viewModel.pegaPerfilFotoUsuario(this)
        if (uri !=null) {
            Glide.with(this).load(uri.toString()).into(binding.profileImage)


        } else {
            binding.profileImage.setImageResource(R.drawable.padrao)
//            Toast.makeText(this, uri, Toast.LENGTH_SHORT).show()

        }

        binding.ibcamera.setOnClickListener {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            camera.launch(cameraIntent)


        }

        binding.ibgaleria.setOnClickListener {
            gallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))

        }

//        binding.imageView3.isClickable = true
        binding.imageView3.setOnClickListener {
            val userUpdated = User(user.id, binding.editTextTextPersonName.text.toString(), user.email, user.senha, uri.toString())

            viewModel.atualizarUsuario(userUpdated)

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
        builder.setTitle("Permiss??es Negadas")
        builder.setMessage("Para utilizar o app ?? necess??rio aceitar as permiss??es")
        builder.setCancelable(false)
        builder.setPositiveButton("Confirmar", DialogInterface.OnClickListener { dialogInterface, i ->
            finish()
        })

        val dialog = builder.create()
        dialog.show()
    }

    private fun observer() {
        viewModel.register.observe(this) {
            if (it !=null) {

                user = it
                binding.editTextTextPersonName.setText(user.nome)

            } else {
                Toast.makeText(this, "VAZIO", Toast.LENGTH_LONG).show()

            }
        }
    }

}