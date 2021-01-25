package com.example.stockspokedex.data.entities.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore

@Entity(primaryKeys = ["companyID"])
data class CompanyEntity(
    @ColumnInfo var companyName: String,
    @ColumnInfo var companyTicker: String,
    @ColumnInfo var companyID: String = "",
    @ColumnInfo var checklistID: String = "",
    @ColumnInfo var stockID: String = "",
    @ColumnInfo var bullishThesisFileID: String = "",
    @ColumnInfo var priceTargetFileID: String = "",
    @ColumnInfo var discountedCashFlowFileID: String = "",
    @ColumnInfo var isActive: Boolean = true,
) {
    @Ignore
    var checklistEntity: ChecklistEntity? = null

    @Ignore
    var stockEntity: StockEntity? = null

    @Ignore
    var fileEntity: FileEntity? = null

    fun toHashMap(): HashMap<String, Any> =
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
    }
}
