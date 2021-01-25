package com.example.stockspokedex.interactors

import com.example.stockspokedex.data.daos.UserDao
import com.example.stockspokedex.data.database.FirestoreUtils
import com.example.stockspokedex.data.entities.db.UserEntity
import com.example.stockspokedex.models.UserInteractor
import com.example.stockspokedex.utils.AppUtils
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserInteractorImpl @Inject constructor(
    private val db: UserDao,
    firestoreUtils: FirestoreUtils
) : UserInteractor {

    private val usersCollection = firestoreUtils.getUsersCollection()

    override fun insertUser(user: UserEntity) {
        db.insertUser(user)
    }

    override fun deleteUser(user: UserEntity) {
        db.deleteUser(user)
    }

    override fun updateUser(user: UserEntity) {
        db.updateUser(user)
    }

    override suspend fun getUserFromFirestoreDB(uid: String): UserEntity? {
        return try {
            val user = usersCollection.document(uid).get().await().toObject(UserEntity::class.java)
            user?.uid = uid
            user
        } catch (e: Exception) {
            AppUtils.reportCrash(e)
            null
        }
    }
}