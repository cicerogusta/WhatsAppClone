package com.ciceropinheiro.whatsapp_clone.extensions

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

fun Fragment.navigateTo(navigation: NavDirections) {
    findNavController().navigate(navigation)
}