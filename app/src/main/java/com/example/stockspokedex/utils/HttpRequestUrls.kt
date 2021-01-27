package com.example.stockspokedex.utils

object HttpRequestUrls {
    fun getStockHistoricalDataURL(ticker: String) = "https://apidojo-yahoo-finance-v1.p.rapidapi.com/stock/v3/get-historical-data?symbol=${ticker}&region=US"
}