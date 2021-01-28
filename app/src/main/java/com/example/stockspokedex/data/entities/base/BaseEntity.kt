package com.example.stockspokedex.data.entities.base

import androidx.room.Ignore
import com.google.gson.Gson
import com.google.gson.GsonBuilder

abstract class BaseEntity {
    @Ignore
    val gson: Gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()

    abstract fun toHashMap(): HashMap<String, Any>
    abstract fun toJson(): String
}