package com.example.stockspokedex.ui.viewstates

import com.example.stockspokedex.ui.base.BaseViewState

class SettingsViewState : BaseViewState() {
    var isLogoutComplete = false

    fun reset(){
        isLogoutComplete = false
    }
}