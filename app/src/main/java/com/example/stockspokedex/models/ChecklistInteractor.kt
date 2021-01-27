package com.example.stockspokedex.models

import com.example.stockspokedex.data.entities.db.ChecklistEntity

interface ChecklistInteractor {
    suspend fun insertChecklist(checklist: ChecklistEntity): ChecklistEntity?
    fun deleteChecklist(checklist: ChecklistEntity)
    fun deleteChecklists(checklistIDs: List<String>)
    fun updateChecklist(checklist: ChecklistEntity)
    fun getChecklist(checklistID: String): ChecklistEntity
    fun getAllChecklists(): List<ChecklistEntity>
}