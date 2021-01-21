package com.example.stockspokedex.data.daos

import androidx.room.*
import com.example.stockspokedex.data.entities.UserEntity

@Dao
interface UserDao {
    /**
     * Inserts a user to the cache.
     */
    @Insert
    fun insertUser(user: UserEntity)

    /**
     * Deletes a user from the cache.
     */
    @Delete
    fun deleteUser(user: UserEntity)

    /**
     * Deletes a list of users from the cache.
     */
    @Query("delete from UserEntity where uid in (:userIds)")
    fun deleteCompanies(userIds: List<String>)

    /**
     * Updates a user in the cache.
     */
    @Update
    fun updateUser(user: UserEntity)

    /**
     * Returns a user with uid = [id].
     * @param id is the id of the company requested.
     */
    @Query("select * from UserEntity where uid = :id")
    fun getUser(id: String): UserEntity
}
