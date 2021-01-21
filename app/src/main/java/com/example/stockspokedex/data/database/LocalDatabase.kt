package com.example.stockspokedex.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.stockspokedex.data.daos.ChecklistDao
import com.example.stockspokedex.data.daos.CompanyDao
import com.example.stockspokedex.data.daos.FileDao
import com.example.stockspokedex.data.daos.UserDao
import com.example.stockspokedex.data.entities.ChecklistEntity
import com.example.stockspokedex.data.entities.CompanyEntity
import com.example.stockspokedex.data.entities.FileEntity
import com.example.stockspokedex.data.entities.UserEntity

@Database(
    entities = [ChecklistEntity::class, UserEntity::class, CompanyEntity::class, FileEntity::class],
    version = 1,
    exportSchema = false
)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun checklistDao(): ChecklistDao
    abstract fun companyDao(): CompanyDao
    abstract fun userDao(): UserDao
    abstract fun fileDao(): FileDao
}