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

    fun toHashMap(): HashMap<String, Any> =
        hashMapOf(
            FIELD_FILE_ID to fileID,
            FIELD_FILE_NAME to fileName,
            FIELD_DOWNLOAD_LINK to downloadLink,
            FIELD_IS_ACTIVE to isActive,
        )

    companion object {
        const val FIELD_FILE_ID = "fileID"
        const val FIELD_FILE_NAME = "fileName"
        const val FIELD_DOWNLOAD_LINK = "downloadLink"
        const val FIELD_IS_ACTIVE = "isActive"
    }
}