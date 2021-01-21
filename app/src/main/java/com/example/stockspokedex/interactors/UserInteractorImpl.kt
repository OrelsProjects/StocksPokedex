package com.example.stockspokedex.interactors

import com.example.stockspokedex.data.daos.UserDao
import com.example.stockspokedex.data.entities.UserEntity
import com.example.stockspokedex.models.UserInteractor
import javax.inject.Inject

class UserInteractorImpl @Inject constructor(private val db: UserDao) : UserInteractor {

    override fun insertUser(user: UserEntity) {
        db.insertUser(user)
    }

    override fun deleteUser(user: UserEntity) {
        db.deleteUser(user)
    }

    override fun updateUser(user: UserEntity) {
        db.updateUser(user)
    }

    override fun getUser(uid: String): UserEntity = db.getUser(uid)
}