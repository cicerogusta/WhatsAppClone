package com.ciceropinheiro.whatsapp_clone

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ciceropinheiro.whatsapp_clone.data.model.User
import com.ciceropinheiro.whatsapp_clone.data.repository.FirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContatosViewModel @Inject constructor(private val repository: FirebaseRepository): ViewModel() {

    private val _users = MutableLiveData<MutableList<User>>()
    val users: MutableLiveData<MutableList<User>>
        get() = _users

    fun retornaContatos() {
        repository.getAllUsers(_users)
    }

}
