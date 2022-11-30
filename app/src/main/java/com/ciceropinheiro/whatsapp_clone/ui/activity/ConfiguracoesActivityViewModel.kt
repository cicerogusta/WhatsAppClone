package com.ciceropinheiro.whatsapp_clone.ui.activity

import android.content.Context
import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import com.ciceropinheiro.whatsapp_clone.data.repository.FirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ConfiguracoesActivityViewModel @Inject constructor(private val repository: FirebaseRepository) :
    ViewModel() {

        fun salvarImagem(context: Context, bitmap: Bitmap) {
            repository.saveUserImage(bitmap, context)

        }

    }
