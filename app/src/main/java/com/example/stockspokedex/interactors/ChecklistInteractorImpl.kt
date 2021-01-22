package com.example.stockspokedex.interactors

import androidx.lifecycle.LiveData
import com.example.stockspokedex.data.daos.ChecklistDao
import com.example.stockspokedex.data.entities.ChecklistEntity
import com.example.stockspokedex.models.ChecklistInteractor
import javax.inject.Inject

class ChecklistInteractorImpl @Inject constructor(private val db: ChecklistDao) :
    ChecklistInteractor {

    override fun insertChecklist(checklist: ChecklistEntity) =
        db.insertChecklist(checklist)


    override fun deleteChecklist(checklist: ChecklistEntity) {
        db.deleteChecklist(checklist)
    }

    override fun deleteChecklists(checklistIDs: List<String>) {
        db.deleteChecklists(checklistIDs)
    }

    override fun updateChecklist(checklist: ChecklistEntity) {
        db.updateChecklist(checklist)
    }

    override fun getChecklist(checklistID: String): ChecklistEntity = db.getChecklist(checklistID)

    override fun getAllChecklists(): List<ChecklistEntity> = db.getAllChecklists()
}