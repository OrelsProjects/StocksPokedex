package com.example.stockspokedex.ui.fragments.main

import androidx.lifecycle.ViewModelProvider
import com.example.stockspokedex.R
import com.example.stockspokedex.ui.base.BaseFragment
import com.example.stockspokedex.ui.viewmodels.LoginViewModel
import com.example.stockspokedex.ui.viewstates.LoginViewState

class LoginFragment : BaseFragment<LoginViewModel, LoginViewState>() {

    private lateinit var viewModel: LoginViewModel

    override fun initViewModel() {
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun getViewModel(): LoginViewModel = viewModel

    override fun updateUI(state: LoginViewState) {

    }

    override fun attachClickListeners() {

    }

    override fun getLayoutResourceFile(): Int = R.layout.fragment_login
}