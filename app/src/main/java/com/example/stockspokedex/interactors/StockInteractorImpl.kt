package com.example.stockspokedex.interactors

import com.example.stockspokedex.data.daos.StockDao
import com.example.stockspokedex.data.database.FirestoreUtils
import com.example.stockspokedex.data.entities.db.StockEntity
import com.example.stockspokedex.models.StockInteractor
import com.example.stockspokedex.utils.AppUtils
import com.example.stockspokedex.utils.Constants.API_HOST_RAPID
import com.example.stockspokedex.utils.Constants.API_TOKEN_RAPID
import com.example.stockspokedex.utils.HttpRequestUrls
import kotlinx.coroutines.tasks.await
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import javax.inject.Inject

class StockInteractorImpl @Inject constructor(
    private val db: StockDao,
    private val firestoreUtils: FirestoreUtils
) : StockInteractor {

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

    override suspend fun insertStock(uid: String, stockEntity: StockEntity): StockEntity? {
        return try {
            val stockDocument = firestoreUtils.getStocksCollection(uid).document()
            stockEntity.stockID = stockDocument.id
            stockDocument.set(stockEntity.toHashMap()).await()
            db.insertStock(stockEntity)
            stockEntity
        } catch (e: Exception) {
            AppUtils.reportCrash(e)
            null
        }
    }

    override suspend fun updateStock(uid: String, stockEntity: StockEntity): StockEntity? {
        return try {
            firestoreUtils.getStocksCollection(uid).document(stockEntity.stockID).update(stockEntity.toHashMap()).await()
            db.updateStock(stockEntity)
            stockEntity
        } catch (e: Exception) {
            AppUtils.reportCrash(e)
            null
        }
    }

    override suspend fun getStocksFromFirestore(uid: String): List<StockEntity> =
        firestoreUtils.getStocksCollection(uid).get().await().toObjects(StockEntity::class.java)

    override fun cacheStocks(list: List<StockEntity>) = db.insertStocks(list)

    override fun getAllStocksFromCache(): List<StockEntity> =
        db.getAllStocks()

    override fun clear() = db.clear()

}