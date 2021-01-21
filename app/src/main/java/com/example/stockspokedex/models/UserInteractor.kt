package com.example.stockspokedex.models

import com.example.stockspokedex.data.entities.UserEntity

interface UserInteractor {
    fun insertUser(user: UserEntity)
    fun deleteUser(user: UserEntity)
    fun updateUser(user: UserEntity)
    fun getUser(uid: String): UserEntity
}