package com.example.stockspokedex.models

import com.example.stockspokedex.data.entities.db.UserEntity

interface UserInteractor {
    fun insertUser(user: UserEntity)
    fun deleteUser(user: UserEntity)
    fun updateUser(user: UserEntity)
    fun getCurrentUser(): UserEntity?
    fun clear()
    suspend fun isUserRegistered(uid: String?): Boolean
    suspend fun getUserFromFirestoreDB(uid: String?): UserEntity?
    suspend fun createUnregisteredUser(userEntity: UserEntity)
}