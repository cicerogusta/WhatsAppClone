package com.ciceropinheiro.whatsapp_clone.ui.fragments.login

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
import com.ciceropinheiro.whatsapp_clone.databinding.FragmentLoginBinding
import com.ciceropinheiro.whatsapp_clone.ui.base.BaseFragment
import com.ciceropinheiro.whatsapp_clone.util.UiState
import com.example.firebasewithmvvm.util.isValidEmail
import com.example.firebasewithmvvm.util.toast
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {

    lateinit var navigation: NavDirections
    override val viewModel: LoginViewModel by hiltNavGraphViewModels(R.id.nav_graph)
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLoginBinding  = FragmentLoginBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupListeners(binding)
    }

    override fun onStart() {
        super.onStart()
        viewModel.getCurrentUser()
        observerCurrentUser()
    }

    private fun observerCurrentUser() {
        viewModel.currentUser.observe(viewLifecycleOwner) {
            if (it == true) {
                navigation = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
                callFragment(navigation)
            }
        }
    }

    private fun setupListeners(binding: FragmentLoginBinding) {
        binding.tvCadastro.isClickable = true
        binding.tvCadastro.setOnClickListener {
            navigation = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            callFragment(navigation)
        }

        binding.buttonLogar.setOnClickListener {
            if (validation()) {
                val user = User(email = binding.campoEmail.text.toString(), senha = binding.campoSenha.text.toString())
                viewModel.login(user.email, user.senha)
                observer()
            }
        }
    }

    private fun callFragment(navigation: NavDirections) {
        findNavController().navigate(navigation)
    }

    fun observer(){
        viewModel.login.observe(viewLifecycleOwner) { state ->
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
                    navigation = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
                    callFragment(navigation)
                }
            }
        }
    }

    fun validation(): Boolean {
        var isValid = true

        if (binding.campoEmail.text.isNullOrEmpty()) {
            isValid = false
            toast("Email não pode estar vazio!")

        } else {
            if (binding.campoSenha.text.isNullOrEmpty()) {
                isValid = false
                toast("Senha não pode estar vazio!")
            }

        }

        return isValid
    }

}