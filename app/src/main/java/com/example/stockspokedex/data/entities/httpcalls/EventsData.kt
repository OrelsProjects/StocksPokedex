package com.example.stockspokedex.data.entities.httpcalls

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class EventsData {
    @SerializedName("date")
    @Expose
    var date: Int? = null

    @SerializedName("numerator")
    @Expose
    var numerator: Int? = null

    @SerializedName("denominator")
    @Expose
    var denominator: Int? = null

    @SerializedName("splitRatio")
    @Expose
    var splitRatio: String? = null

    @SerializedName("type")
    @Expose
    var type: String? = null

    @SerializedName("data")
    @Expose
    var data: String? = null
}