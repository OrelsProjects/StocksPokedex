package com.example.stockspokedex.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import com.example.stockspokedex.ui.base.BaseViewModel
import com.example.stockspokedex.ui.viewstates.MainViewState
import javax.inject.Inject

class MainViewModel @ViewModelInject constructor(
) : BaseViewModel<MainViewState>() {
    var testVar = 4

    @Inject
    lateinit var mainViewState: MainViewState

    override fun getViewState(): MainViewState = mainViewState
}