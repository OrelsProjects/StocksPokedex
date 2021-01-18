package com.example.stockspokedex.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import com.example.stockspokedex.ui.base.BaseViewModel
import com.example.stockspokedex.ui.viewstates.AddStockViewState

class AddStockViewModel @ViewModelInject constructor(
    private val viewState: AddStockViewState
) : BaseViewModel<AddStockViewState>() {

    override fun getViewState(): AddStockViewState = viewState
}