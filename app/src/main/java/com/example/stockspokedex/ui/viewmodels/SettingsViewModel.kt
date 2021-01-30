package com.example.stockspokedex.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import com.example.stockspokedex.models.FirebaseAuthInteractor
import com.example.stockspokedex.ui.base.BaseViewModel
import com.example.stockspokedex.ui.viewstates.SettingsViewState

class SettingsViewModel @ViewModelInject constructor(
    private val viewState: SettingsViewState,
    private val authInteractor: FirebaseAuthInteractor
) : BaseViewModel<SettingsViewState>() {

    override fun getViewState(): SettingsViewState = viewState

    fun handleSignOut() {
        authInteractor.signOut()
        viewState.isLogoutComplete = true
        updateUI()
    }
}