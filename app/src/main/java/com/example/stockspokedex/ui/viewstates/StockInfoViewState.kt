package com.example.stockspokedex.ui.viewstates

import androidx.lifecycle.MutableLiveData
import com.example.stockspokedex.data.entities.db.ChecklistEntity
import com.example.stockspokedex.data.entities.db.CompanyEntity
import com.example.stockspokedex.data.entities.db.StockEntity
import com.example.stockspokedex.ui.base.BaseViewState

class StockInfoViewState : BaseViewState() {
    var isStockSaveDone = false
    var isCompanyExistsInDB = false
    var isEditStockDone = false

    var state: MutableLiveData<State> = MutableLiveData(State.New)

    var companyToEdit: CompanyEntity? = null
    var stockToEdit: StockEntity? = null
    var checklistToEdit: ChecklistEntity? = null

    fun reset() {
        isStockSaveDone = false
        isCompanyExistsInDB = false
        isEditStockDone = false
        companyToEdit = null
        stockToEdit = null
        checklistToEdit = null
    }

    enum class State {
        New,
        Edit
    }
}