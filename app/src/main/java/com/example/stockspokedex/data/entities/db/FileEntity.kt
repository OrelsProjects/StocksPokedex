package com.example.stockspokedex.data.entities.db

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["fileID"])
data class FileEntity(
    @ColumnInfo var fileID: String = "",
    @ColumnInfo var fileName: String = "",
    @ColumnInfo var downloadLink: String = "",
    @ColumnInfo var isActive: Boolean = true
) {
}