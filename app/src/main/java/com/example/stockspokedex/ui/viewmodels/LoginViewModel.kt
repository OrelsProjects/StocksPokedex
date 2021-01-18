package com.example.stockspokedex.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import com.example.stockspokedex.ui.base.BaseViewModel
import com.example.stockspokedex.ui.viewstates.LoginViewState
import javax.inject.Inject

class LoginViewModel @ViewModelInject constructor(
) : BaseViewModel<LoginViewState>() {


    @Inject
    lateinit var loginViewState: LoginViewState

    override fun getViewState(): LoginViewState = loginViewState
}