package com.example.stockspokedex.ui.viewstates

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.stockspokedex.data.entities.db.CompanyEntity
import com.example.stockspokedex.ui.base.BaseViewState

class AddStockViewState : BaseViewState() {
    var isStockSaveDone = false
    var isCompanyExistsInDB = false
//    var companies: LiveData<List<CompanyEntity>> = MutableLiveData()

    fun reset() {
        isStockSaveDone = false
        isCompanyExistsInDB = false
    }
}