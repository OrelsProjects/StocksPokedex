package com.example.stockspokedex.models

import com.example.stockspokedex.data.entities.db.ChecklistEntity

interface ChecklistInteractor {
    suspend fun insertChecklist(uid: String, checklist: ChecklistEntity): ChecklistEntity?
    suspend fun deleteChecklist(checklist: ChecklistEntity)
    suspend fun deleteChecklists(checklistIDs: List<String>)
    suspend fun updateChecklist(uid: String, checklist: ChecklistEntity): ChecklistEntity?
    suspend fun getChecklist(checklistID: String): ChecklistEntity
    suspend fun getChecklistsFromFirestore(uid: String): List<ChecklistEntity>
    fun insertChecklists(list: List<ChecklistEntity>)
    fun getAllChecklists(): List<ChecklistEntity>
    fun clear()
}