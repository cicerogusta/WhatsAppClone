package com.ciceropinheiro.whatsapp_clone.ui.fragments.chat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ciceropinheiro.whatsapp_clone.data.model.Mensagem
import com.ciceropinheiro.whatsapp_clone.data.model.User
import com.ciceropinheiro.whatsapp_clone.data.repository.FirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatFragmentViewModel @Inject constructor(private val repository: FirebaseRepository): ViewModel() {

    private val _mensagens = MutableLiveData<MutableList<Mensagem>>()
    val mensagens: MutableLiveData<MutableList<Mensagem>>
        get() = _mensagens

    fun retornaIdRemetente(): String? {
        return repository.getUserId()
    }
    fun enviaMensagem(idRemetente: String, idDestinatario: String, msg: Mensagem) {
        repository.sentMessage(idRemetente, idDestinatario, msg)
    }
    fun retornaMensagem(idRemetente: String, idDestinatario: String) {
        repository.getMessage(idRemetente, idDestinatario, _mensagens)
    }
}