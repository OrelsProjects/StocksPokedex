package com.example.stockspokedex.models

import com.example.stockspokedex.data.entities.db.StockEntity
import okhttp3.Callback

interface StockInteractor {
    fun getStockHistoricalData(ticker: String, callback: Callback)
    suspend fun insertStock(stockEntity: StockEntity): StockEntity?
    fun getAllStocks(): List<StockEntity>
}