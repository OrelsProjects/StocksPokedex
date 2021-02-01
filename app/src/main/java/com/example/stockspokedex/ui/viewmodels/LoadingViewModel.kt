package com.example.stockspokedex.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import com.example.stockspokedex.ui.base.BaseViewModel
import com.example.stockspokedex.ui.viewstates.LoadingViewState

class LoadingViewModel @ViewModelInject constructor(
    private val viewState: LoadingViewState
) : BaseViewModel<LoadingViewState>() {

    override fun getViewState(): LoadingViewState = viewState
}