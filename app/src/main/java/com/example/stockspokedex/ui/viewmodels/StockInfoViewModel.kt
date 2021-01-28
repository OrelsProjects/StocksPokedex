package com.example.stockspokedex.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import com.example.stockspokedex.data.entities.db.ChecklistEntity
import com.example.stockspokedex.data.entities.db.CompanyEntity
import com.example.stockspokedex.data.entities.db.StockEntity
import com.example.stockspokedex.data.entities.httpcalls.YahooFinanceStock
import com.example.stockspokedex.models.ChecklistInteractor
import com.example.stockspokedex.models.CompanyInteractor
import com.example.stockspokedex.models.StockInteractor
import com.example.stockspokedex.ui.base.BaseViewModel
import com.example.stockspokedex.ui.viewstates.StockInfoViewState
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class StockInfoViewModel @ViewModelInject constructor(
    private val viewState: StockInfoViewState,
    private val companyInteractor: CompanyInteractor,
    private val stockInteractor: StockInteractor,
    private val checklistInteractor: ChecklistInteractor
) : BaseViewModel<StockInfoViewState>() {

    override fun getViewState(): StockInfoViewState = viewState

    fun handleSaveStock(
        company: CompanyEntity,
        checklist: ChecklistEntity,
        stock: StockEntity
    ) {
        if (viewState.state.value == StockInfoViewState.State.Edit) {
            editStock(company, stock, checklist)
        } else {
            addStock(company, stock, checklist)
        }
    }

    private fun addStock(company: CompanyEntity, stock: StockEntity, checklist: ChecklistEntity) {
        if (companyInteractor.getCompanyByTickerFromSync(company.companyTicker) != null) {
            viewState.isCompanyExistsInDB = true
        } else {
            stockInteractor.getStockHistoricalData(company.companyTicker, object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    response.body?.let {
                        val yahooFinanceStock = YahooFinanceStock.jsonToObject(it.string())
                        stock.currentPrice =
                            yahooFinanceStock?.prices?.get(0)?.close.toString()
                    }
                    networkScope.launch {
                        val stockInsertResult = stockInteractor.insertStock(stock)
                        val checklistInsertResult = checklistInteractor.insertChecklist(checklist)
                        company.checklistID = checklist.checklistID
                        company.stockID = stock.stockID
                        val companyInsertResult = companyInteractor.insertCompany(company)
                        if (companyInsertResult == null) {
                            // Todo was not inserted
                        }
                        if (stockInsertResult == null) {
                            // Todo was not inserted
                        }
                        if (checklistInsertResult == null) {
                            // Todo was not inserted
                        }
                        finishSave()
                    }
                }

                override fun onFailure(call: Call, e: IOException) {
                    // todo failed to get info from rapidapi
                    finishSave()
                }
            })
        }
    }

    private fun editStock(
        company: CompanyEntity,
        stock: StockEntity,
        checklist: ChecklistEntity
    ) {
        networkScope.launch {
            val companyUpdateResult = companyInteractor.updateCompany(company)
            val stockUpdateResult = stockInteractor.updateStock(stock)
            val checklistUpdateResult = checklistInteractor.updateChecklist(checklist)
            if (companyUpdateResult == null) {
                // Todo was not updated
            }
            if (stockUpdateResult == null) {
                // Todo was not updated
            }
            if (checklistUpdateResult == null) {
                // Todo was not updated
            }
            finishEdit()
        }
    }

    fun setState(
        isEdit: Boolean,
        company: CompanyEntity? = null,
        stock: StockEntity? = null,
        checklist: ChecklistEntity? = null
    ) {
        if (isEdit) {
            viewState.state.postValue(StockInfoViewState.State.Edit)
            viewState.companyToEdit = company
            viewState.stockToEdit = stock
            viewState.checklistToEdit = checklist
        } else {
            viewState.state.postValue(StockInfoViewState.State.New)
        }
        updateUI()
    }

    private fun finishSave() {
        viewState.isStockSaveDone = true
        updateUI()
    }

    private fun finishEdit() {
        viewState.isEditStockDone = true
        updateUI()
    }
}