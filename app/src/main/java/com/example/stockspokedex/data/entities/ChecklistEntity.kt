package com.example.stockspokedex.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["checklistID"])
data class ChecklistEntity(
    @ColumnInfo var checklistID: String = "",
    @ColumnInfo var tenK: Boolean = false,
    @ColumnInfo var ceo: Boolean = false,
    @ColumnInfo var conferenceCall: Boolean = false,
    @ColumnInfo var tenQ: Boolean = false,
    @ColumnInfo var investorsPresentation: Boolean = false,
    @ColumnInfo var news: Boolean = false,
    @ColumnInfo var isActive: Boolean = false
)