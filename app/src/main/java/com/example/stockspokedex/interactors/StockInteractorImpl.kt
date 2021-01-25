package com.example.stockspokedex.interactors

import android.content.Context
import com.example.stockspokedex.R
import com.example.stockspokedex.data.daos.StockDao
import com.example.stockspokedex.data.database.FirestoreUtils
import com.example.stockspokedex.data.entities.db.StockEntity
import com.example.stockspokedex.models.StockInteractor
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

    override fun getYahooFinanceStockData(ticker: String, callback: Callback) {

        val client = OkHttpClient()
        val baseUrlRapidAPI = "https://cloud.iexapis.com/"
        val stockRapidAPI = "stock/v3/get-historical-data?symbol=${ticker}&region=US\""
        val baseUrlIEX = "https://cloud-sse.iexapis.com/"
        val tokenRapid = "apidojo-yahoo-finance-v1.p.rapidapi.com"
        val stockUrlIEX =
            baseUrlIEX + "stable/stocksUSNoUTP?token=${context.getString(R.string.iex_api_token)}&symbols=spy,ibm,twtr"
        val stockURLRapid = baseUrlRapidAPI + stockRapidAPI
        val request = Request.Builder()
            .url("https://apidojo-yahoo-finance-v1.p.rapidapi.com/stock/v3/get-historical-data?symbol=${ticker}&region=US")
            .get()
            .addHeader("x-rapidapi-key", "d5521624a8msh964b295244bd92bp1b86e0jsn6dee14943944")
            .addHeader("x-rapidapi-host", "apidojo-yahoo-finance-v1.p.rapidapi.com")
            .build()

        val call: Call = client.newCall(request)
        call.enqueue(callback)
    }

    override suspend fun insertStock(stockEntity: StockEntity) {
        stocksCollection.document().set(stockEntity.toHashMap()).await()
        db.insertStock(stockEntity)
    }

    override fun getAllStocks(): List<StockEntity> =
        db.getAllStocks()

}