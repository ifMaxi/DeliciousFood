package com.maxidev.deliciousfood.core_local.data.local

import androidx.room.TypeConverter

class Converter {

    @TypeConverter
    fun convertToList(value: String): List<String> = value.split(",").map {
        it.trim()
    }

    @TypeConverter
    fun convertToString(list: List<String>): String = list.joinToString(separator = ",")
}