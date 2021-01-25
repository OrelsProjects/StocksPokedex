package com.example.stockspokedex.data.entities.db

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
    @ColumnInfo var isActive: Boolean = true
) {

    fun toHashMap(): HashMap<String, Any> =
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

    companion object {
        const val FIELD_CHECKLIST_ID = "checklistID"
        const val FIELD_TEN_K = "tenK"
        const val FIELD_CEO = "ceo"
        const val FIELD_CONFERENCE_CALL = "conferenceCall"
        const val FIELD_TEN_Q = "tenQ"
        const val FIELD_INVESTORS_PRESENTATION = "investorsPresentation"
        const val FIELD_NEWS = "news"
        const val FIELD_IS_ACTIVE = "isActive"
    }
}