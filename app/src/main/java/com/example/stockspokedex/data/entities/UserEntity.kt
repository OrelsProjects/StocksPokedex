package com.example.stockspokedex.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["uid"])
data class UserEntity(
    @ColumnInfo var uid: String = "",
    @ColumnInfo var gender: String = "",
    @ColumnInfo var phoneNumber: String = "",
    @ColumnInfo var email: String = "",
    @ColumnInfo var nickName: String = "",
    @ColumnInfo var firstName: String = "",
    @ColumnInfo var lastName: String = "",
    @ColumnInfo var isActive: Boolean = true
)