package com.ciceropinheiro.whatsapp_clone.ui.activity

import androidx.lifecycle.ViewModel
import com.ciceropinheiro.whatsapp_clone.data.repository.FirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val repository: FirebaseRepository) :
    ViewModel() {
        fun deslogaUsuario() {
            repository.logout()

        }
    }
