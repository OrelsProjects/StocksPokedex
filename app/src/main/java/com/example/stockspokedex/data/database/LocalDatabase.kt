package com.example.stockspokedex.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.stockspokedex.data.daos.*
import com.example.stockspokedex.data.entities.db.*

@Database(
    entities = [ChecklistEntity::class, UserEntity::class, CompanyEntity::class, FileEntity::class, StockEntity::class],
    version = 4,
    exportSchema = false
)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun checklistDao(): ChecklistDao
    abstract fun companyDao(): CompanyDao
    abstract fun userDao(): UserDao
    abstract fun fileDao(): FileDao
    abstract fun stockDao(): StockDao
}