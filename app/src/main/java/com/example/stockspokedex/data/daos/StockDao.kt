package com.example.stockspokedex.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.stockspokedex.data.entities.db.StockEntity

@Dao
interface StockDao {

    /**
     * Insert a StockEntity object to the cache.
     * @param stockEntity is the object to insert.
     */
    @Insert
    fun insertStock(stockEntity: StockEntity)

    /**
     * Insert a list of stocks to the cache.
     * @param list is the list of the stocks to add.
     */
    @Insert
    fun insertStocks(list: List<StockEntity>)

    /**
     * Returns all the stock entities from the cache.
     */
    @Query("select * from StockEntity")
    fun getAllStocks(): List<StockEntity>

    /**
     * Updates a StockEntity object in the cache.
     * @param stockEntity is the new object.
     */
    @Update
    fun updateStock(stockEntity: StockEntity)

    /**
     * Deletes all the data from the table.
     */
    @Query("Delete from StockEntity")
    fun clear()
}