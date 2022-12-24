package com.ciceropinheiro.whatsapp_clone.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ciceropinheiro.whatsapp_clone.R
import com.ciceropinheiro.whatsapp_clone.data.model.User
import com.ciceropinheiro.whatsapp_clone.databinding.ItemContatoBinding

class ContatosAdapter(
    private var listaContatos: MutableList<User>, private val context: Context
) :
    RecyclerView.Adapter<ContatosAdapter.MyViewHolder?>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemLista = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contato, parent, false)
        return MyViewHolder(itemLista)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        val contato = listaContatos[position]
        holder.binding.contato = contato
        if (contato.foto !=null) {
            Glide.with(context).load(Uri.parse(contato.foto)).into(holder.binding.profileImage)
        } else {
            holder.binding.profileImage.setImageResource(R.drawable.padrao)
        }

    }

    override fun getItemCount(): Int {
        return listaContatos.size
    }

    inner class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val binding: ItemContatoBinding by lazy { ItemContatoBinding.bind(itemView) }
    }

}