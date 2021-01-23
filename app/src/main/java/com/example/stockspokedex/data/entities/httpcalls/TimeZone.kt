package com.example.stockspokedex.data.entities.httpcalls

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class TimeZone {
    @SerializedName("gmtOffset")
    @Expose
    var gmtOffset: Int? = null
}