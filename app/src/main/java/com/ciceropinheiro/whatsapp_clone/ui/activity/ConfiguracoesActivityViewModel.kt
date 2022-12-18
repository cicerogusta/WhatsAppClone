package com.ciceropinheiro.whatsapp_clone.ui.activity

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.ViewModel
import com.ciceropinheiro.whatsapp_clone.data.repository.FirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ConfiguracoesActivityViewModel @Inject constructor(private val repository: FirebaseRepository) :
    ViewModel() {


    fun salvarImagemGaleria(context: Context, uri: Uri) {
        repository.saveUserImageGalery(uri, context)

    }

    fun pegaPerfilUsuario(context: Context): Uri? {
        return repository.getUserProfilePhoto(context)
    }

    fun salvarImagemCamera(context: Context, bitmap: Bitmap) {
        repository.saveUserImageCamera(bitmap, context)

    }

}
