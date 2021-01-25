package com.example.stockspokedex.data.entities.httpcalls

import com.google.gson.GsonBuilder
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class YahooFinanceStock {
    @SerializedName("prices")
    @Expose
    var prices: List<Price>? = null

    @SerializedName("isPending")
    @Expose
    var isPending: Boolean? = null

    @SerializedName("firstTradeDate")
    @Expose
    var firstTradeDate: Int? = null

    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("timeZone")
    @Expose
    var timeZone: TimeZone? = null

    @SerializedName("eventsData")
    @Expose
    var eventsData: List<EventsData>? = null

    companion object {
        fun jsonToObject(json: String): YahooFinanceStock? {
            if (json == "Not Found") return null
            val gson = GsonBuilder().create()
            return gson.fromJson(json, YahooFinanceStock::class.java)
        }
    }
}