package com.example.stockspokedex.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import com.example.stockspokedex.ui.base.BaseViewModel
import com.example.stockspokedex.ui.viewstates.MainViewState

class MainViewModel @ViewModelInject constructor(
    private val viewState: MainViewState
) : BaseViewModel<MainViewState>() {

    override fun getViewState(): MainViewState = viewState
}