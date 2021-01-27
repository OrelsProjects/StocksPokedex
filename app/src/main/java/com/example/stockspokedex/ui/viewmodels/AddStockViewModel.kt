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
import com.example.stockspokedex.ui.viewstates.AddStockViewState
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class AddStockViewModel @ViewModelInject constructor(
    private val viewState: AddStockViewState,
    private val companyInteractor: CompanyInteractor,
    private val stockInteractor: StockInteractor,
    private val checklistInteractor: ChecklistInteractor
) : BaseViewModel<AddStockViewState>() {

    override fun getViewState(): AddStockViewState = viewState

    fun handleSaveStock(company: CompanyEntity, checklist: ChecklistEntity, targetPrice: String) {
        if (companyInteractor.getCompanyByTicker(company.companyTicker) != null) {
            viewState.isCompanyExistsInDB = true
        } else {
            stockInteractor.getStockHistoricalData(company.companyTicker, object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    var stock: StockEntity? = null
                    response.body?.let {
                        stock = StockEntity()
                        val yahooFinanceStock = YahooFinanceStock.jsonToObject(it.string())
                        stock?.currentPrice = yahooFinanceStock?.prices?.get(0)?.close.toString()
                        stock?.targetPrice = targetPrice
                    }
                    networkScope.launch {
                        var stockInsertResult: StockEntity? = null
                        if (stock != null) {
                            stockInsertResult = stockInteractor.insertStock(stock!!)
                        }
                        val checklistInsertResult = checklistInteractor.insertChecklist(checklist)
                        company.checklistID = checklist.checklistID
                        stock?.let { company.stockID = it.stockID }
                        val companyInsertResult = companyInteractor.insertCompany(company)
                        if (companyInsertResult == null) {
                            // Todo was not inserted
                        }
                        if(stockInsertResult == null){
                            // Todo was not inserted
                        }
                        if(checklistInsertResult == null){
                            // Todo was not inserted
                        }
                        finishSave()
                    }
                }

                override fun onFailure(call: Call, e: IOException) {
                    finishSave()
                }

                fun finishSave() {
                    viewState.isStockSaveDone = true
                    updateUI()
                }
            })

        }

    }
}