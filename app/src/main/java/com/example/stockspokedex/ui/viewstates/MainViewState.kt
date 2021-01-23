package com.example.stockspokedex.ui.viewstates

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.stockspokedex.data.entities.db.CompanyEntity
import com.example.stockspokedex.ui.base.BaseViewState

class MainViewState : BaseViewState() {
    var companies: LiveData<List<CompanyEntity>> = MutableLiveData()

    fun reset(){

    }
}