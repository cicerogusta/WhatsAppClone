package com.ciceropinheiro.whatsapp_clone.data.repository

import android.content.Context
import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import com.ciceropinheiro.whatsapp_clone.data.model.User
import com.ciceropinheiro.whatsapp_clone.util.UiState

interface FirebaseRepository {
    fun loginUser(email: String, senha: String, result: (UiState<String>) -> Unit)
    fun forgotPassword(email: String, result: (UiState<String>) -> Unit)
    fun logout()
    fun registerUser(user: User, result: (UiState<String>) -> Unit)
    fun getUserProfileInDatabase(liveData: MutableLiveData<User>)
    fun getAllUsers(liveData: MutableLiveData<MutableList<User>>, liveDataProfile: MutableLiveData<User>)
    fun isCurrentUser() : Boolean
    fun saveUserImage(imagem: Bitmap, context: Context)
}