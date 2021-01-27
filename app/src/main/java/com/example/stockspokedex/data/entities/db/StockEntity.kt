package com.example.stockspokedex.data.entities.db

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["stockID"])
data class StockEntity(
    @ColumnInfo var stockID: String = "",
    @ColumnInfo var currentPrice: String = "",
    @ColumnInfo var targetPrice: String = "",
    @ColumnInfo var isActive: Boolean = true
) {
    fun isABuy(): Boolean {
        return targetPrice >= currentPrice
    }

    fun toHashMap(): HashMap<String, Any> =
        hashMapOf(
            FIELD_STOCK_ID to stockID,
            FIELD_CURRENT_PRICE to currentPrice,
            FIELD_TARGET_PRICE to targetPrice,
            FIELD_IS_ACTIVE to isActive,
        )


    companion object {
        const val FIELD_STOCK_ID = "stockID"
        const val FIELD_CURRENT_PRICE = "currentPrice"
        const val FIELD_TARGET_PRICE = "targetPrice"
        const val FIELD_IS_ACTIVE = "isActive"
    }
}