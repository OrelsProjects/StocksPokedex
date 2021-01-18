package com.example.stockspokedex.ui.fragments

import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.stockspokedex.MainActivity
import com.example.stockspokedex.R
import com.example.stockspokedex.ui.base.BaseFragment
import com.example.stockspokedex.ui.viewmodels.LoginViewModel
import com.example.stockspokedex.ui.viewstates.LoginViewState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_login.*

@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginViewModel, LoginViewState>(), View.OnClickListener {

    private lateinit var viewModel: LoginViewModel

    override fun initViewModel() {
        viewModel = ViewModelProvider(requireActivity()).get(LoginViewModel::class.java)
    }

    override fun getViewModel(): LoginViewModel = viewModel

    override fun updateUI(state: LoginViewState) {
        if(state.isLoginSuccessful){
            activity?.navHostFragment?.findNavController()?.navigate(R.id.action_loginFragment_to_mainFragment)
        }
    }

    override fun attachClickListeners() {
        loginButton.setOnClickListener(this)
    }

    override fun getLayoutResourceFile(): Int = R.layout.fragment_login
    override fun onClick(view: View) {
        when (view.id) {
            R.id.loginButton -> {
                viewModel.handleLogin()
            }
        }
    }
}