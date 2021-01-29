package com.example.stockspokedex.data.entities.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import com.example.stockspokedex.data.entities.base.BaseEntity
import com.google.gson.GsonBuilder
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["companyID"])
data class CompanyEntity(
    @Expose
    @SerializedName("companyName")
    @ColumnInfo var companyName: String = "",
    @Expose
    @SerializedName("companyTicker")
    @ColumnInfo var companyTicker: String = "",
    @Expose
    @SerializedName("companyID")
    @ColumnInfo var companyID: String = "",
    @Expose
    @SerializedName("checklistID")
    @ColumnInfo var checklistID: String = "",
    @Expose
    @SerializedName("stockID")
    @ColumnInfo var stockID: String = "",
    @Expose
    @SerializedName("bullishThesisFileID")
    @ColumnInfo var bullishThesisFileID: String = "",
    @Expose
    @SerializedName("priceTargetFileID")
    @ColumnInfo var priceTargetFileID: String = "",
    @Expose
    @SerializedName("discountedCashFlowFileID")
    @ColumnInfo var discountedCashFlowFileID: String = "",
    @Expose
    @SerializedName("isActive")
    @ColumnInfo var isActive: Boolean = true,
) : BaseEntity() {

    @Ignore
    var checklistEntity: ChecklistEntity? = null

    @Ignore
    var stockEntity: StockEntity? = null

    @Ignore
    var fileEntity: FileEntity? = null

    override fun toHashMap(): HashMap<String, Any> =
        hashMapOf(
            FIELD_COMPANY_ID to companyID,
            FIELD_COMPANY_NAME to companyName,
            FIELD_COMPANY_TICKER to companyTicker,
            FIELD_CHECKLIST_ID to checklistID,
            FIELD_STOCK_ID to stockID,
            FIELD_BULLISH_THESIS_FILE_ID to bullishThesisFileID,
            FIELD_PRICE_TARGET_FILE_ID to priceTargetFileID,
            FIELD_DISCOUNTED_CASH_FLOW_FILE_ID to discountedCashFlowFileID,
            FIELD_IS_ACTIVE to isActive,
        )

    override fun toJson(): String =
        gson.toJson(this, CompanyEntity::class.java)

    companion object {
        const val FIELD_COMPANY_ID = "companyID"
        const val FIELD_COMPANY_NAME = "companyName"
        const val FIELD_COMPANY_TICKER = "companyTicker"
        const val FIELD_CHECKLIST_ID = "checklistID"
        const val FIELD_STOCK_ID = "stockID"
        const val FIELD_BULLISH_THESIS_FILE_ID = "bullishThesisFileID"
        const val FIELD_PRICE_TARGET_FILE_ID = "priceTargetFileID"
        const val FIELD_DISCOUNTED_CASH_FLOW_FILE_ID = "discountedCashFlowFileID"
        const val FIELD_IS_ACTIVE = "isActive"

        fun fromJson(json: String): CompanyEntity =
            GsonBuilder().create().fromJson(json, CompanyEntity::class.java)
    }
}
