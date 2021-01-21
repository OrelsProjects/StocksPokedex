package com.example.stockspokedex.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.stockspokedex.data.entities.FileEntity

@Dao
interface FileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFile(file: FileEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFilesList(file: ArrayList<FileEntity>)

    @Query("SELECT * FROM FileEntity WHERE fileID = :fileID AND isActive = 1")
    fun getFile(fileID: String): FileEntity?

    @Query("SELECT * FROM FileEntity WHERE fileID IN (:fileIDs) AND isActive = 1")
    fun getFiles(fileIDs: List<String>): List<FileEntity>

    @Query("DELETE FROM FileEntity WHERE fileID = :fileID")
    fun deleteFile(fileID: String)

    @Query("DELETE FROM FileEntity WHERE fileID in (:files)")
    fun deleteFiles(files: List<String>)

    @Query("DELETE  FROM FileEntity")
    fun clearTable()
}