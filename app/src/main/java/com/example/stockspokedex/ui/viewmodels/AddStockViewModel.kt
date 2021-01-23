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
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException
import java.util.*

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
            stockInteractor.getYahooFinanceStockData(company.companyTicker, object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    var stock: StockEntity? = null
                    response.body?.let {
                        stock = StockEntity()
                        val yahooFinanceStock = YahooFinanceStock.jsonToObject(it.string())
                        stock!!.stockID = Date().time.toString()
                        stock!!.currentPrice = yahooFinanceStock.prices?.get(0)?.close.toString()
                        stock!!.targetPrice = targetPrice
                        stockInteractor.insertStock(stock!!)
                    }
                    checklistInteractor.insertChecklist(checklist)
                    company.checklistID = checklist.checklistID
                    stock?.let { company.stockID = it.stockID }
                    companyInteractor.insertCompany(company)
                    finishSave()
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