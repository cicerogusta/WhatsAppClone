package com.ciceropinheiro.whatsapp_clone.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.ciceropinheiro.whatsapp_clone.R
import com.ciceropinheiro.whatsapp_clone.data.model.Mensagem
import com.ciceropinheiro.whatsapp_clone.data.model.User
import com.ciceropinheiro.whatsapp_clone.databinding.ItemContatoBinding
import com.ciceropinheiro.whatsapp_clone.databinding.MensagemDestinatarioBinding
import com.ciceropinheiro.whatsapp_clone.databinding.MensagemRemetenteBinding

class ChatAdapter(
    private var listaMensagens: MutableList<Mensagem>, private val context: Context, private val idUsuario: String
) :
    RecyclerView.Adapter<ChatAdapter.MyViewHolder?>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var item: ViewBinding? = null
       if (viewType == TIPO_REMETENTE) {
            item = MensagemRemetenteBinding.bind(LayoutInflater.from(parent.context).inflate(R.layout.mensagem_remetente, parent, false))




       } else if (viewType == TIPO_DESTINATARIO) {
           item = MensagemDestinatarioBinding.bind(LayoutInflater.from(parent.context).inflate(R.layout.mensagem_destinatario, parent, false))


       }
        return MyViewHolder(item!!.root)

    }

    val TIPO_REMETENTE = 1
    val TIPO_DESTINATARIO = 0

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        val mensagem = listaMensagens[position]
        val msg = mensagem.mensagem
        val imagem = mensagem.imagem
        if (imagem != null) {
            Glide.with(context).load(imagem).into(holder.imagem)
            holder.mensagem.visibility = View.GONE
        } else {
            holder.mensagem.setText(msg)
            holder.imagem.visibility = View.GONE

        }

    }

    override fun getItemCount(): Int {
        return listaMensagens.size
    }

    override fun getItemViewType(position: Int): Int {
        val mensagem = listaMensagens[position]
        if (idUsuario == mensagem.idUsuario) {
            return TIPO_REMETENTE
        }
        return TIPO_DESTINATARIO


    }

    inner class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val imagem: ImageView = itemView.rootView.findViewById<ImageView>(R.id.mensagemFoto)
        val mensagem: TextView = itemView.rootView.findViewById<TextView>(R.id.mensagemTexto)
    }

}