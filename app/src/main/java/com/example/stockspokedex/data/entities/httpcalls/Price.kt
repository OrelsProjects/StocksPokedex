package com.example.stockspokedex.data.entities.httpcalls

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class Price{

    @SerializedName("date")
    @Expose
    private val date: Int? = null

    @SerializedName("open")
    @Expose
    private val open: Double? = null

    @SerializedName("high")
    @Expose
    private val high: Double? = null

    @SerializedName("low")
    @Expose
    private val low: Double? = null

    @SerializedName("close")
    @Expose
    val close: Double? = null

    @SerializedName("volume")
    @Expose
    private val volume: Int? = null

    @SerializedName("adjclose")
    @Expose
    private val adjclose: Double? = null

    @SerializedName("numerator")
    @Expose
    private val numerator: Int? = null

    @SerializedName("denominator")
    @Expose
    private val denominator: Int? = null

    @SerializedName("splitRatio")
    @Expose
    private val splitRatio: String? = null

    @SerializedName("type")
    @Expose
    private val type: String? = null

    @SerializedName("data")
    @Expose
    private val data: String? = null


}