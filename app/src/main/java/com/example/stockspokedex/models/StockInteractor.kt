package com.example.stockspokedex.models

import com.example.stockspokedex.data.entities.db.StockEntity
import okhttp3.Callback

interface StockInteractor {
    suspend fun insertStock(uid: String, stockEntity: StockEntity): StockEntity?
    suspend fun updateStock(uid: String, stockEntity: StockEntity): StockEntity?
    suspend fun getStocksFromFirestore(uid: String): List<StockEntity>
    fun cacheStocks(list: List<StockEntity>)
    fun getStockHistoricalData(ticker: String, callback: Callback)
    fun getAllStocksFromCache(): List<StockEntity>
    fun clear()
}