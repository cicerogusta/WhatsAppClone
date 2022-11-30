package com.ciceropinheiro.whatsapp_clone.util

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

object Permissao {

    fun validarPermissoes(
        permissoes: Array<String>,
        activity: Activity?,
        requestCode: Int
    ): Boolean {
        if (Build.VERSION.SDK_INT >= 23) {
            val listaPermissoes: MutableList<String> = ArrayList()

            for (permissao in permissoes) {
                val temPermissao = ContextCompat.checkSelfPermission(
                    activity!!,
                    permissao
                ) == PackageManager.PERMISSION_GRANTED
                if (!temPermissao) listaPermissoes.add(permissao)
            }

            if (listaPermissoes.isEmpty()) return true
            val novasPermissoes = listaPermissoes.toTypedArray()

            //Solicita permissão
            ActivityCompat.requestPermissions(activity!!, novasPermissoes, requestCode)
        }
        return true
    }

}