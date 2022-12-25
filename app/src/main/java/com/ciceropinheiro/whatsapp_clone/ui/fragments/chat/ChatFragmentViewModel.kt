package com.ciceropinheiro.whatsapp_clone.ui.fragments.chat

import androidx.lifecycle.ViewModel
import com.ciceropinheiro.whatsapp_clone.data.repository.FirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatFragmentViewModel @Inject constructor(repository: FirebaseRepository): ViewModel() {
}