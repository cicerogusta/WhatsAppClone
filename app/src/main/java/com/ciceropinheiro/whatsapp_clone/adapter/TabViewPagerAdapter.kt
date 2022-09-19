package com.ciceropinheiro.whatsapp_clone.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ciceropinheiro.whatsapp_clone.R
import com.ciceropinheiro.whatsapp_clone.ui.fragments.chamadas.ChamadasFragment
import com.ciceropinheiro.whatsapp_clone.ui.fragments.conversas.ConversasFragment
import com.ciceropinheiro.whatsapp_clone.ui.fragments.status.StatusFragment

class TabViewPagerAdapter(fa: Fragment) : FragmentStateAdapter(fa) {
    val tabs = arrayOf(R.string.conversas, R.string.status, R.string.chamadas)
    val fragments = arrayOf(ConversasFragment(),StatusFragment(), ChamadasFragment())
    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}