package com.example.stockspokedex.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

abstract class BaseViewModel<S : BaseViewState>(
) : ViewModel() {

    private val stateLiveData = MutableLiveData<BaseViewState>()

    private val networkJob = Job()
    protected val networkScope = CoroutineScope(Dispatchers.IO + networkJob)

    fun getState(): MutableLiveData<BaseViewState> {
        return this.stateLiveData
    }

    fun updateUI() {
        stateLiveData.postValue(getViewState())
    }

    abstract fun getViewState(): S
}