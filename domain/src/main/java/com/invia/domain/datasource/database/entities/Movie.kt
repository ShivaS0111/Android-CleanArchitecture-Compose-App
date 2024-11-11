package com.invia.domain.datasource.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.invia.domain.model.Image

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey
    val id: Int?,
    val image: Image?,
    val language: String?,
    val name: String?,
)