package com.example.stockspokedex.ui.viewstates

import com.example.stockspokedex.data.entities.db.UserEntity
import com.example.stockspokedex.ui.base.BaseViewState

class AuthViewState : BaseViewState() {
    var isLoginSuccessful: Boolean = false
    var isLoginFailed: Boolean = false
    var connectedUser: UserEntity? = null

    fun reset() {
        isLoginSuccessful = false
        isLoginFailed = false
        connectedUser = null
    }
}