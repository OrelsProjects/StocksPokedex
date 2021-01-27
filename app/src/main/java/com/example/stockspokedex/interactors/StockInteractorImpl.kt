package com.example.stockspokedex.interactors

import android.content.Context
import com.example.stockspokedex.data.daos.StockDao
import com.example.stockspokedex.data.database.FirestoreUtils
import com.example.stockspokedex.data.entities.db.StockEntity
import com.example.stockspokedex.models.StockInteractor
import com.example.stockspokedex.utils.AppUtils
import com.example.stockspokedex.utils.Constants.API_HOST_RAPID
import com.example.stockspokedex.utils.Constants.API_TOKEN_RAPID
import com.example.stockspokedex.utils.HttpRequestUrls
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.tasks.await
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import javax.inject.Inject

class StockInteractorImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    firestoreUtils: FirestoreUtils,
    private val db: StockDao
) : StockInteractor {

    private val stocksCollection = firestoreUtils.getStocksCollection()
    private val client = OkHttpClient()

    override fun getStockHistoricalData(ticker: String, callback: Callback) {
        val request = Request.Builder()
            .url(HttpRequestUrls.getStockHistoricalDataURL(ticker))
            .get()
            .addHeader("x-rapidapi-key", API_TOKEN_RAPID)
            .addHeader("x-rapidapi-host", API_HOST_RAPID)
            .build()

        val call: Call = client.newCall(request)
        call.enqueue(callback)
    }

    override suspend fun insertStock(stockEntity: StockEntity): StockEntity? {
        return try{
        val stockDocument = stocksCollection.document()
        stockEntity.stockID = stockDocument.id
        stockDocument.set(stockEntity.toHashMap()).await()
        db.insertStock(stockEntity)
        stockEntity
        } catch (e: Exception) {
            AppUtils.reportCrash(e)
            null
        }
    }

    override fun getAllStocks(): List<StockEntity> =
        db.getAllStocks()

}