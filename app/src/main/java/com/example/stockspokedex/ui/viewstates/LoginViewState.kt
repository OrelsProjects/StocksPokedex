package com.example.stockspokedex.ui.viewstates

import com.example.stockspokedex.ui.base.BaseViewState

class LoginViewState : BaseViewState() {
    var isLoginSuccessful: Boolean = false
    var isLoginFailed: Boolean = false

    fun reset(){
        isLoginSuccessful = false
        isLoginFailed = false
    }
}