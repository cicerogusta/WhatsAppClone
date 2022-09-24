package com.ciceropinheiro.whatsapp_clone.util

import android.util.Base64

fun codificarBase64(texto : String) : String {
    return Base64.encodeToString(texto.toByteArray(), Base64.DEFAULT).replace("(\\n|\\r)", "")

}

fun decodificarBase64(textoCodificado : String) : String {
    return String(Base64.decode(textoCodificado, Base64.DEFAULT))


}