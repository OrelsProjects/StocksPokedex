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
    private val firestoreUtils: FirestoreUtils
) : UserInteractor {

    override fun insertUser(user: UserEntity) {
        db.insertUser(user)
    }

    override fun deleteUser(user: UserEntity) {
        db.deleteUser(user)
    }

    override fun updateUser(user: UserEntity) {
        db.updateUser(user)
    }

    override fun getCurrentUser(): UserEntity? = db.getCurrentUser()

    override fun clear() = db.clear()

    override suspend fun getUserFromFirestoreDB(uid: String?): UserEntity? {
        if (uid == null) return null
        return try {
            val user = firestoreUtils.getUserDocument(uid).get().await()
                .toObject(UserEntity::class.java)
            user?.uid = uid
            user
        } catch (e: Exception) {
            AppUtils.reportCrash(e)
            null
        }
    }

    override suspend fun isUserRegistered(uid: String?): Boolean {
        if (uid == null) return false
        val res = firestoreUtils.getUserDocument(uid).get().await()
        val user = res.toObject(UserEntity::class.java)
        return user?.uid == uid
    }

    override suspend fun createUnregisteredUser(userEntity: UserEntity) {
        val data = userEntity.toHashMap()
        firestoreUtils.getUsersCollection().document(userEntity.uid).set(data)
            .await()
    }
}