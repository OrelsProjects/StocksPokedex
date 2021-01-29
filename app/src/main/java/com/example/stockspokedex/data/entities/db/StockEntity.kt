package com.example.stockspokedex.data.entities.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.example.stockspokedex.data.entities.base.BaseEntity
import com.google.gson.GsonBuilder
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["stockID"])
data class StockEntity(
    @Expose
    @SerializedName("stockID")
    @ColumnInfo var stockID: String = "",
    @Expose
    @SerializedName("currentPrice")
    @ColumnInfo var currentPrice: String = "",
    @Expose
    @SerializedName("priceTarget")
    @ColumnInfo var priceTarget: String = "",
    @Expose
    @SerializedName("isActive")
    @ColumnInfo var isActive: Boolean = true
) : BaseEntity() {
    fun isABuy(): Boolean {
        return priceTarget.toInt() >= currentPrice.toFloat()
    }

    override fun toHashMap(): HashMap<String, Any> =
        hashMapOf(
            FIELD_STOCK_ID to stockID,
            FIELD_CURRENT_PRICE to currentPrice,
            FIELD_TARGET_PRICE to priceTarget,
            FIELD_IS_ACTIVE to isActive,
        )

    override fun toJson(): String = gson.toJson(this, StockEntity::class.java)


    companion object {
        const val FIELD_STOCK_ID = "stockID"
        const val FIELD_CURRENT_PRICE = "currentPrice"
        const val FIELD_TARGET_PRICE = "priceTarget"
        const val FIELD_IS_ACTIVE = "isActive"

        fun fromJson(json: String): StockEntity =
            GsonBuilder().create().fromJson(json, StockEntity::class.java)
    }
}