package com.ciceropinheiro.whatsapp_clone.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class User(
    var id: String = "",
    var email: String = "",
    var senha: String = ""

    )