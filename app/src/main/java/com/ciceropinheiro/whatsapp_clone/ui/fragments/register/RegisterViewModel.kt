package com.ciceropinheiro.whatsapp_clone.ui.fragments.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ciceropinheiro.whatsapp_clone.data.model.User
import com.ciceropinheiro.whatsapp_clone.data.repository.FirebaseRepository
import com.ciceropinheiro.whatsapp_clone.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
   private val repository: FirebaseRepository

) : ViewModel() {

   private val _register = MutableLiveData<UiState<String>>()
   val register: LiveData<UiState<String>>
      get() = _register

   fun registerUser(user: User) {
      _register.value = UiState.Loading
      repository.registerUser(user) {

         _register.value = it

      }
   }

}