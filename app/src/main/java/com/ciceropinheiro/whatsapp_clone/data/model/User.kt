package com.ciceropinheiro.whatsapp_clone.data.model

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class User(
    var id: String? = "",
    var nome: String = "",
    var email: String = "",
    var senha: String = "",
    var foto: String? = null

    ) {

    fun map(): Map<String, Any> {
        val userMap = HashMap<String, Any>()
        userMap.put("email", email)
        userMap.put("nome", nome)
        foto?.let { userMap.put("foto", it) }
        return userMap
    }

}