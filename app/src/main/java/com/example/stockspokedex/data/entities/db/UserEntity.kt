package com.example.stockspokedex.data.entities.db

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
) {

    fun toHashMap(): HashMap<String, Any> =
        hashMapOf(
            FIELD_USER_UID to uid,
            FIELD_GENDER to gender,
            FIELD_PHONE_NUMBER to phoneNumber,
            FIELD_EMAIL to email,
            FIELD_NICK_NAME to nickName,
            FIELD_FIRST_NAME to firstName,
            FIELD_LAST_NAME to lastName,
            FIELD_IS_ACTIVE to isActive,
        )

    companion object {
        const val FIELD_USER_UID = "uid"
        const val FIELD_GENDER = "gender"
        const val FIELD_PHONE_NUMBER = "phoneNumber"
        const val FIELD_EMAIL = "email"
        const val FIELD_NICK_NAME = "nickName"
        const val FIELD_FIRST_NAME = "firstName"
        const val FIELD_LAST_NAME = "lastName"
        const val FIELD_IS_ACTIVE = "isActive"
    }
}