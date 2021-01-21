package com.example.stockspokedex.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["companyID"])
data class CompanyEntity(
    @ColumnInfo var companyName: String,
    @ColumnInfo var companyTicker: String,
    @ColumnInfo var companyID: String = "",
    @ColumnInfo var checklistID: String = "",
    @ColumnInfo var bullishThesisFileID: String = "",
    @ColumnInfo var priceTargetFileID: String = "",
    @ColumnInfo var discountedCashFlowFileID: String = "",
    @ColumnInfo var isActive: Boolean = false
)