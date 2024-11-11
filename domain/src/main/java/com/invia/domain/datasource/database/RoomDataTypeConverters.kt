package com.invia.domain.datasource.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.invia.domain.model.Image
import java.util.Date

class RoomDataTypeConverters {

    @TypeConverter
    fun fromJsonString(value: String): Image {
        return Gson().fromJson(value, Image::class.java)
    }

    @TypeConverter
    fun toJsonString(data: Image): String {
        return Gson().toJson(data)
    }

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return timestamp?.let { Date(it) }
    }
}