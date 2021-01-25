package com.example.stockspokedex.models

import com.example.stockspokedex.data.entities.db.UserEntity

interface UserInteractor {
    fun insertUser(user: UserEntity)
    fun deleteUser(user: UserEntity)
    fun updateUser(user: UserEntity)
    suspend fun getUserFromFirestoreDB(uid: String): UserEntity?
}