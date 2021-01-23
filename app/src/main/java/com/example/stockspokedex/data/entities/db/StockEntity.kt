package com.example.stockspokedex.data.entities.db

import androidx.room.Entity

@Entity(primaryKeys = ["stockID"])
data class StockEntity(
    var stockID: String = "",
    var currentPrice: String = "",
    var targetPrice: String = "",
) {
    fun isBuy() = targetPrice <= currentPrice
}