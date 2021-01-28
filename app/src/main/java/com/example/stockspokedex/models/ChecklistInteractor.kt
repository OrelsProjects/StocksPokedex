package com.example.stockspokedex.models

import com.example.stockspokedex.data.entities.db.ChecklistEntity

interface ChecklistInteractor {
    suspend fun insertChecklist(checklist: ChecklistEntity): ChecklistEntity?
    suspend fun deleteChecklist(checklist: ChecklistEntity)
    suspend fun deleteChecklists(checklistIDs: List<String>)
    suspend fun updateChecklist(checklist: ChecklistEntity): ChecklistEntity?
    suspend fun getChecklist(checklistID: String): ChecklistEntity
    fun getAllChecklists(): List<ChecklistEntity>
}