package com.example.stockspokedex.models

import androidx.lifecycle.LiveData
import com.example.stockspokedex.data.entities.ChecklistEntity

interface ChecklistInteractor {
    fun insertChecklist(checklist: ChecklistEntity)
    fun deleteChecklist(checklist: ChecklistEntity)
    fun deleteChecklists(checklistIDs: List<String>)
    fun updateChecklist(checklist: ChecklistEntity)
    fun getChecklist(checklistID: String): ChecklistEntity
    fun getAllChecklists(): LiveData<List<ChecklistEntity>>
}