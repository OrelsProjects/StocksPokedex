package com.example.stockspokedex.data.daos

import androidx.room.*
import com.example.stockspokedex.data.entities.db.ChecklistEntity

@Dao
interface ChecklistDao {
    /**
     * Inserts a checklist to the cache.
     */
    @Insert
    fun insertChecklist(checklist: ChecklistEntity)

    /**
     * Deletes a checklist from the cache.
     */
    @Delete
    fun deleteChecklist(checklist: ChecklistEntity)

    /**
     * Deletes a list of checklists from the cache.
     */
    @Query("delete from ChecklistEntity where checklistID in (:checklistIds)")
    fun deleteChecklists(checklistIds: List<String>)

    /**
     * Updates a checklist in the cache.
     */
    @Update
    fun updateChecklist(checklist: ChecklistEntity)

    /**
     * Fetches a list of all the checklists.
     * @return a livedata object of the companies.
     */
    @Query("select * from ChecklistEntity where isActive = 1")
    fun getAllChecklists(): List<ChecklistEntity>

    /**
     * Fetches a user with checklistID = [id].
     * @param id is the id of the checklist requested.
     */
    @Query("select * from ChecklistEntity where checklistID = :id")
    fun getChecklist(id: String): ChecklistEntity
}