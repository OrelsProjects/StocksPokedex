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
    private val firestoreUtils: FirestoreUtils
) :
    ChecklistInteractor {


    override suspend fun insertChecklist(
        uid: String,
        checklist: ChecklistEntity
    ): ChecklistEntity? {
        return try {
            val checklistDocument = firestoreUtils.getChecklistsCollection(uid).document()
            checklist.checklistID = checklistDocument.id
            checklistDocument.set(checklist.toHashMap()).await()
            db.insertChecklist(checklist)
            checklist
        } catch (e: Exception) {
            AppUtils.reportCrash(e)
            null
        }
    }


    override suspend fun deleteChecklist(checklist: ChecklistEntity) {
        db.deleteChecklist(checklist)
    }

    override suspend fun deleteChecklists(checklistIDs: List<String>) {
        db.deleteChecklists(checklistIDs)
    }

    override suspend fun updateChecklist(
        uid: String,
        checklist: ChecklistEntity
    ): ChecklistEntity? {
        return try {
            firestoreUtils.getChecklistsCollection(uid).document(checklist.checklistID)
                .update(checklist.toHashMap())
                .await()
            db.updateChecklist(checklist)
            checklist
        } catch (e: Exception) {
            AppUtils.reportCrash(e)
            null
        }
    }

    override suspend fun getChecklist(checklistID: String): ChecklistEntity =
        db.getChecklist(checklistID)

    override suspend fun getChecklistsFromFirestore(uid: String): List<ChecklistEntity> =
        firestoreUtils.getChecklistsCollection(uid).get().await()
            .toObjects(ChecklistEntity::class.java)

    override fun insertChecklists(list: List<ChecklistEntity>) =
        db.insertChecklists(list)

    override fun getAllChecklists(): List<ChecklistEntity> = db.getAllChecklists()

    override fun clear() = db.clear()
}