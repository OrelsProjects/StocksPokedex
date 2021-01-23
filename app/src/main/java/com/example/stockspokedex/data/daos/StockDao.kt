package com.example.stockspokedex.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.stockspokedex.data.entities.db.StockEntity

@Dao
interface StockDao {

    @Insert
    fun insertStock(stockEntity: StockEntity)

    @Query("select * from StockEntity")
    fun getAllStocks(): List<StockEntity>
}