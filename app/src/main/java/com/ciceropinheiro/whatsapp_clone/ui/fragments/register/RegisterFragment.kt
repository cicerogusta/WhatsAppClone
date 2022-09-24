package com.ciceropinheiro.whatsapp_clone.ui.fragments.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.ciceropinheiro.whatsapp_clone.R
import com.ciceropinheiro.whatsapp_clone.data.model.User
import com.ciceropinheiro.whatsapp_clone.databinding.FragmentRegisterBinding
import com.ciceropinheiro.whatsapp_clone.ui.base.BaseFragment
import com.ciceropinheiro.whatsapp_clone.ui.fragments.login.LoginFragmentDirections
import com.ciceropinheiro.whatsapp_clone.util.UiState
import com.ciceropinheiro.whatsapp_clone.util.codificarBase64
import com.example.firebasewithmvvm.util.isValidEmail
import com.example.firebasewithmvvm.util.toast
import dagger.hilt.android.AndroidEntryPoint

class RegisterFragment : BaseFragment<FragmentRegisterBinding, RegisterViewModel>() {

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRegisterBinding = FragmentRegisterBinding.inflate(layoutInflater, container, false)

    lateinit var navigation: NavDirections
    override val viewModel: RegisterViewModel by hiltNavGraphViewModels(R.id.nav_graph)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button2.setOnClickListener {
            SignUpUser()
        }

    }

    private fun SignUpUser() {
        if (validation()) {
            val user = User()
            user.nome = binding.campoNome.text.toString()
            user.email = binding.campoEmailRegistro.text.toString()
            user.senha = binding.campoSenhaRegistro.text.toString()
            user.id = codificarBase64(user.email)
            viewModel.registerUser(user)
            observer()

        }
    }

    fun validation(): Boolean {
        var isValid = true

        if (binding.campoNome.text.isNullOrEmpty()) {
            isValid = false
            toast("Email não pode estar vazio!")

        } else {
            if (binding.campoEmailRegistro.text.isNullOrEmpty()) {
                isValid = false
                toast("Email não pode estar vazio!")
            } else {
                if (!binding.campoEmailRegistro.text.toString().isValidEmail()) {
                    isValid = false
                    toast("Insira um email valido")
                } else {
                    if (binding.campoSenhaRegistro.text.isNullOrEmpty()) {
                        isValid = false
                        toast("Senha não pode estar vazia")
                    } else {
                        if (binding.campoSenhaRegistro.text.toString().length < 8) {
                            isValid = false
                            toast("A senha deve conter 8 caracteres")
                        }
                    }
                }
            }

        }

        return isValid
    }

    private fun callFragment(navigation: NavDirections) {
        findNavController().navigate(navigation)
    }

    fun observer(){
        viewModel.register.observe(viewLifecycleOwner) { state ->
            when(state){
                is UiState.Loading -> {
//                    binding.loginProgress.show()
                }
                is UiState.Failure -> {
//                    binding.loginProgress.hide()
                    toast(state.error)
                }
                is UiState.Success -> {
//                    binding.loginProgress.hide()
                    toast(state.data)
                    navigation = RegisterFragmentDirections.actionRegisterFragmentToHomeFragment()
                    callFragment(navigation)
                }
            }
        }
    }

}