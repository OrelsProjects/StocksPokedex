package com.example.stockspokedex.data.entities.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.example.stockspokedex.data.entities.base.BaseEntity
import com.google.gson.GsonBuilder
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["checklistID"])
data class ChecklistEntity(
    @Expose
    @SerializedName("checklistID")
    @ColumnInfo var checklistID: String = "",
    @Expose
    @SerializedName("tenK")
    @ColumnInfo var tenK: Boolean = false,
    @Expose
    @SerializedName("ceo")
    @ColumnInfo var ceo: Boolean = false,
    @Expose
    @SerializedName("conferenceCall")
    @ColumnInfo var conferenceCall: Boolean = false,
    @Expose
    @SerializedName("tenQ")
    @ColumnInfo var tenQ: Boolean = false,
    @Expose
    @SerializedName("investorsPresentation")
    @ColumnInfo var investorsPresentation: Boolean = false,
    @Expose
    @SerializedName("news")
    @ColumnInfo var news: Boolean = false,
    @Expose
    @SerializedName("isActive")
    @ColumnInfo var isActive: Boolean = true
) : BaseEntity() {

    override fun toHashMap(): HashMap<String, Any> =
        hashMapOf(
            FIELD_CHECKLIST_ID to checklistID,
            FIELD_TEN_K to tenK,
            FIELD_CEO to ceo,
            FIELD_CONFERENCE_CALL to conferenceCall,
            FIELD_TEN_Q to tenQ,
            FIELD_INVESTORS_PRESENTATION to investorsPresentation,
            FIELD_NEWS to news,
            FIELD_IS_ACTIVE to isActive,
        )

    override fun toJson(): String = gson.toJson(this, ChecklistEntity::class.java)

    companion object {
        const val FIELD_CHECKLIST_ID = "checklistID"
        const val FIELD_TEN_K = "tenK"
        const val FIELD_CEO = "ceo"
        const val FIELD_CONFERENCE_CALL = "conferenceCall"
        const val FIELD_TEN_Q = "tenQ"
        const val FIELD_INVESTORS_PRESENTATION = "investorsPresentation"
        const val FIELD_NEWS = "news"
        const val FIELD_IS_ACTIVE = "isActive"

        fun fromJson(json: String): ChecklistEntity =
            GsonBuilder().create().fromJson(json, ChecklistEntity::class.java)
    }
}