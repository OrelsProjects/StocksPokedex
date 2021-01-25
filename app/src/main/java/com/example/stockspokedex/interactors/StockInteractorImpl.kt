package com.example.stockspokedex.interactors

import android.content.Context
import com.example.stockspokedex.data.daos.StockDao
import com.example.stockspokedex.data.entities.db.StockEntity
import com.example.stockspokedex.models.StockInteractor
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Callback
import javax.inject.Inject

class StockInteractorImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val firestoreDB: FirebaseFirestore,
    private val db: StockDao
) : StockInteractor {

    override fun getYahooFinanceStockData(ticker: String, callback: Callback) {
        firestoreDB.collection("users").get().addOnCompleteListener {
            if(it.isSuccessful){
                println()
            } else {
                println()
            }
        }
//        val client = OkHttpClient()
//        val baseUrlRapidAPI = "https://cloud.iexapis.com/"
//        val stockRapidAPI = "stock/v3/get-historical-data?symbol=${ticker}&region=US\""
//        val baseUrlIEX = "https://cloud.iexapis.com/"
//        val tokenIEX = "sk_784b977de41f43659207426d1e809756"
//        val tokenRapid = "apidojo-yahoo-finance-v1.p.rapidapi.com"
//        val stockUrlIEX =
//            "https://cloud-sse.iexapis.com/stable/stocksUSNoUTP?token=${context.getString(R.string.iex_api_token)}&symbols=spy,ibm,twtr"
//        val request = Request.Builder()
//            .url(stockUrlIEX)
//            .get()
////            .addHeader("x-rapidapi-host", "apidojo-yahoo-finance-v1.p.rapidapi.com")
//            .build()
//
//        val call: Call = client.newCall(request)
//        call.enqueue(callback)
    }

    override fun insertStock(stockEntity: StockEntity) =
        db.insertStock(stockEntity)

    override fun getAllStocks(): List<StockEntity> =
        db.getAllStocks()

}