package com.ciceropinheiro.whatsapp_clone.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ciceropinheiro.whatsapp_clone.R
import com.ciceropinheiro.whatsapp_clone.data.model.Mensagem
import com.ciceropinheiro.whatsapp_clone.data.model.User
import com.ciceropinheiro.whatsapp_clone.databinding.ItemContatoBinding

class ChatAdapter(
    private var listaMensagens: MutableList<Mensagem>, private val context: Context
) :
    RecyclerView.Adapter<ChatAdapter.MyViewHolder?>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemLista = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contato, parent, false)
        return MyViewHolder(itemLista)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        val mensagem = listaMensagens[position]

    }

    override fun getItemCount(): Int {
        return listaMensagens.size
    }

    inner class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val binding: ItemContatoBinding by lazy { ItemContatoBinding.bind(itemView) }
    }

}