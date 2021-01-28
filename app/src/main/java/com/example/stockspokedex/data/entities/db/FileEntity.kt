package com.example.stockspokedex.data.entities.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.example.stockspokedex.data.entities.base.BaseEntity
import com.google.gson.GsonBuilder
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["fileID"])
data class FileEntity(
    @Expose
    @SerializedName("fileID")
    @ColumnInfo var fileID: String = "",
    @Expose
    @SerializedName("fileName")
    @ColumnInfo var fileName: String = "",
    @Expose
    @SerializedName("downloadLink")
    @ColumnInfo var downloadLink: String = "",
    @Expose
    @SerializedName("isActive")
    @ColumnInfo var isActive: Boolean = true
) : BaseEntity() {

    override fun toHashMap(): HashMap<String, Any> =
        hashMapOf(
            FIELD_FILE_ID to fileID,
            FIELD_FILE_NAME to fileName,
            FIELD_DOWNLOAD_LINK to downloadLink,
            FIELD_IS_ACTIVE to isActive,
        )

    override fun toJson(): String = gson.toJson(this, FileEntity::class.java)

    companion object {
        const val FIELD_FILE_ID = "fileID"
        const val FIELD_FILE_NAME = "fileName"
        const val FIELD_DOWNLOAD_LINK = "downloadLink"
        const val FIELD_IS_ACTIVE = "isActive"

        fun fromJson(json: String): FileEntity =
            GsonBuilder().create().fromJson(json, FileEntity::class.java)
    }
}