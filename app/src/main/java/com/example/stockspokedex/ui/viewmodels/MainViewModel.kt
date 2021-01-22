package com.example.stockspokedex.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.example.stockspokedex.models.ChecklistInteractor
import com.example.stockspokedex.models.CompanyInteractor
import com.example.stockspokedex.models.UserInteractor
import com.example.stockspokedex.ui.base.BaseViewModel
import com.example.stockspokedex.ui.viewstates.MainViewState

class MainViewModel @ViewModelInject constructor(
    private val viewState: MainViewState,
    private val companyInteractor: CompanyInteractor,
    private val userInteractor: UserInteractor,
    private val checklistInteractor: ChecklistInteractor
) : BaseViewModel<MainViewState>() {

    override fun getViewState(): MainViewState = viewState

    fun setCompanies() {
        val companies = companyInteractor.getAllCompaniesSync()
        val checklists = checklistInteractor.getAllChecklists()
        companies.forEach { company ->
            val companyPossibleChecklists = checklists.filter { company.checklistID == it.checklistID }
            if(companyPossibleChecklists.isNotEmpty()){
                company.checklistEntity = companyPossibleChecklists[0]
            }
        }
        viewState.companies = MutableLiveData(companies)
        updateUI()
    }
}