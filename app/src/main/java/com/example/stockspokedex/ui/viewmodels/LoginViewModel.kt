package com.example.stockspokedex.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import com.example.stockspokedex.ui.base.BaseViewModel
import com.example.stockspokedex.ui.viewstates.LoginViewState

class LoginViewModel @ViewModelInject constructor(
    private val viewState: LoginViewState
) : BaseViewModel<LoginViewState>() {


    override fun getViewState(): LoginViewState = viewState

    fun handleLogin() {
        // Todo implement login
        viewState.isLoginSuccessful = true
        updateUI()
    }
}