package com.ciceropinheiro.whatsapp_clone.ui.fragments.chat

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.ciceropinheiro.whatsapp_clone.R
import com.ciceropinheiro.whatsapp_clone.adapter.ChatAdapter
import com.ciceropinheiro.whatsapp_clone.data.model.Mensagem
import com.ciceropinheiro.whatsapp_clone.databinding.FragmentChatBinding
import com.ciceropinheiro.whatsapp_clone.ui.activity.MainActivity
import com.ciceropinheiro.whatsapp_clone.ui.base.BaseFragment
import com.ciceropinheiro.whatsapp_clone.util.codificarBase64
import com.example.firebasewithmvvm.util.toast
import dagger.hilt.android.AndroidEntryPoint


class ChatFragment : BaseFragment<FragmentChatBinding, ChatFragmentViewModel>() {
    override val viewModel: ChatFragmentViewModel by hiltNavGraphViewModels(R.id.nav_graph)
    lateinit var mActivity: FragmentActivity
    private val args : ChatFragmentArgs by navArgs()
    private var listaMensagens = mutableListOf<Mensagem>()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentChatBinding = FragmentChatBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let { mActivity = it }
        viewModel.retornaMensagem(viewModel.retornaIdRemetente()!!, codificarBase64(args.user.email))
        observer()
        setUpToolbar()
        if (!args.user.equals(null)) binding.user = args.user
        if (!args.user.foto.equals(null)) {

            Glide.with(this).load(args.user.foto).into(binding.circleIv)
        } else {
            binding.circleIv.setImageResource(R.drawable.padrao)
        }

        binding.floatingActionButton2.setOnClickListener {
            if (binding.editTextTextPersonName2.text.toString().isNotEmpty()) {
                val mensagem = Mensagem()
                mensagem.idUsuario = viewModel.retornaIdRemetente()
                mensagem.mensagem = binding.editTextTextPersonName2.text.toString()
                viewModel.enviaMensagem(viewModel.retornaIdRemetente()!!, codificarBase64(args.user.email), mensagem)
                viewModel.enviaMensagem(codificarBase64(args.user.email ),viewModel.retornaIdRemetente()!!, mensagem)
                binding.editTextTextPersonName2.setText("")

            }

        }

    }

    private fun observer() {
        viewModel.mensagens.observe(viewLifecycleOwner) {
            it.forEach { mensagem ->
                listaMensagens.add(mensagem)
            }
            configuraRecyclerView()
        }
    }

    private fun configuraRecyclerView() {
        binding.chatRecyclerview.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter =
                ChatAdapter(listaMensagens, requireContext(), viewModel.retornaIdRemetente()!!)
            this.adapter?.notifyDataSetChanged()

        }
    }

    private fun setUpToolbar() {

        val mainActivity = mActivity as MainActivity

        binding.toolbar.title = ""

        mainActivity.setSupportActionBar(binding.toolbar)
        val navController = NavHostFragment.findNavController(this)
        NavigationUI.setupActionBarWithNavController(mainActivity,navController)

    }

    override fun onStart() {
        super.onStart()
        configuraRecyclerView()


    }
}