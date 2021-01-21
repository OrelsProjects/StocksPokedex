package com.example.stockspokedex.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import com.example.stockspokedex.data.entities.ChecklistEntity
import com.example.stockspokedex.data.entities.CompanyEntity
import com.example.stockspokedex.models.ChecklistInteractor
import com.example.stockspokedex.models.CompanyInteractor
import com.example.stockspokedex.models.UserInteractor
import com.example.stockspokedex.ui.base.BaseViewModel
import com.example.stockspokedex.ui.viewstates.AddStockViewState

class AddStockViewModel @ViewModelInject constructor(
    private val viewState: AddStockViewState,
    private val companyInteractor: CompanyInteractor,
    private val userInteractor: UserInteractor,
    private val checklistInteractor: ChecklistInteractor
) : BaseViewModel<AddStockViewState>() {

    override fun getViewState(): AddStockViewState = viewState

    fun handleSaveStock(company: CompanyEntity, checklistEntity: ChecklistEntity){
        checklistInteractor.insertChecklist(checklistEntity)
        companyInteractor.insertCompany(company)
        viewState.isStockSaveDone = true
        viewState.companies = companyInteractor.getAllCompanies()
        updateUI()
    }
}