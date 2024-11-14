package com.example.movie.domain.datasource.local.util

import androidx.room.TypeConverter
import com.example.movie.domain.model.ImageData
import com.google.gson.Gson
import java.util.Date

class RoomDataTypeConverters {

    @TypeConverter
    fun fromJsonString(value: String): ImageData {
        return Gson().fromJson(value, ImageData::class.java)
    }

    @TypeConverter
    fun toJsonString(data: ImageData): String {
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