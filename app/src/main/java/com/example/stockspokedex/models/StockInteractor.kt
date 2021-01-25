package com.example.stockspokedex.models

import com.example.stockspokedex.data.entities.db.StockEntity
import okhttp3.Callback

interface StockInteractor {
    fun getYahooFinanceStockData(ticker: String, callback: Callback)
    suspend fun insertStock(stockEntity: StockEntity)
    fun getAllStocks(): List<StockEntity>
}