package com.example.stockspokedex.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import com.example.stockspokedex.models.UserInteractor
import com.example.stockspokedex.ui.base.BaseViewModel
import com.example.stockspokedex.ui.viewstates.LoginViewState
import com.example.stockspokedex.utils.UserUtils
import kotlinx.coroutines.launch

class LoginViewModel @ViewModelInject constructor(
    private val viewState: LoginViewState,
    private val userInteractor: UserInteractor
) : BaseViewModel<LoginViewState>() {

    private val orelID = "7gcf1sL0rcACF7hJCUQw"

    override fun getViewState(): LoginViewState = viewState

    fun handleLogin() {
        networkScope.launch {
            val user = userInteractor.getUserFromFirestoreDB(orelID)
            if (user == null) {
                viewState.isLoginFailed
            } else {
                UserUtils.currentUser = user
                viewState.isLoginSuccessful = true
            }
            updateUI()
        }
    }
}