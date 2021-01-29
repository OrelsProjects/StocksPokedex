package com.example.stockspokedex.data.daos

import androidx.room.*
import com.example.stockspokedex.data.entities.db.UserEntity

/**
 * Contains only the current connected user.
 */
@Dao
interface UserDao {
    /**
     * Inserts a user to the cache.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
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
     * Fetches a user with uid = [id].
     * @param id is the id of the company requested.
     */
    @Query("select * from UserEntity where uid = :id")
    fun getUser(id: String): UserEntity

    /**
     * Returns the current connected user object.
     */
    @Query("Select * from UserEntity")
    fun getCurrentUser(): UserEntity

    @Query("delete from UserEntity")
    fun clear()
}
