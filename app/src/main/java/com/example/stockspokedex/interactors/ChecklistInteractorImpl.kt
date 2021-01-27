package com.example.stockspokedex.interactors

import com.example.stockspokedex.data.daos.ChecklistDao
import com.example.stockspokedex.data.database.FirestoreUtils
import com.example.stockspokedex.data.entities.db.ChecklistEntity
import com.example.stockspokedex.models.ChecklistInteractor
import com.example.stockspokedex.utils.AppUtils
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ChecklistInteractorImpl @Inject constructor(
    private val db: ChecklistDao,
    firestoreUtils: FirestoreUtils
) :
    ChecklistInteractor {

    private val checklistsCollection = firestoreUtils.getChecklistsCollection()

    override suspend fun insertChecklist(checklist: ChecklistEntity): ChecklistEntity? {
        return try {
            val checklistDocument = checklistsCollection.document()
            checklist.checklistID = checklistDocument.id
            checklistDocument.set(checklist.toHashMap()).await()
            db.insertChecklist(checklist)
            checklist
        } catch (e: Exception) {
            AppUtils.reportCrash(e)
            null
        }
    }


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