package com.example.stockspokedex.data.entities.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.example.stockspokedex.data.entities.base.BaseEntity
import com.google.firebase.auth.FirebaseUser
import com.google.gson.GsonBuilder
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["uid"])
data class UserEntity(
    @Expose
    @SerializedName("uid")
    @ColumnInfo var uid: String = "",
    @Expose
    @SerializedName("gender")
    @ColumnInfo var gender: String = "",
    @Expose
    @SerializedName("phoneNumber")
    @ColumnInfo var phoneNumber: String = "",
    @Expose
    @SerializedName("email")
    @ColumnInfo var email: String = "",
    @Expose
    @SerializedName("nickName")
    @ColumnInfo var nickName: String = "",
    @Expose
    @SerializedName("firstName")
    @ColumnInfo var firstName: String = "",
    @Expose
    @SerializedName("lastName")
    @ColumnInfo var lastName: String = "",
    @Expose
    @SerializedName("isActive")
    @ColumnInfo var isActive: Boolean = true
) : BaseEntity() {

    override fun toHashMap(): HashMap<String, Any> =
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

    override fun toJson(): String = gson.toJson(this, UserEntity::class.java)

    companion object {
        const val FIELD_USER_UID = "uid"
        const val FIELD_GENDER = "gender"
        const val FIELD_PHONE_NUMBER = "phoneNumber"
        const val FIELD_EMAIL = "email"
        const val FIELD_NICK_NAME = "nickName"
        const val FIELD_FIRST_NAME = "firstName"
        const val FIELD_LAST_NAME = "lastName"
        const val FIELD_IS_ACTIVE = "isActive"

        fun fromJson(json: String): UserEntity =
            GsonBuilder().create().fromJson(json, UserEntity::class.java)

        fun firebaseUserToEntity(firebaseUser: FirebaseUser?): UserEntity? {
            if (firebaseUser == null) return null
            return UserEntity(
                uid = firebaseUser.uid,
                email = firebaseUser.email ?: ""
            )
        }
    }
}