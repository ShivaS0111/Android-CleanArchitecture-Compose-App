package com.example.movie.data.datasource.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movie.domain.model.ImageData
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey
    val id: Int?,

    @SerializedName("image")
    val image: ImageData?,

    @SerializedName("language")
    val language: String?,

    @SerializedName("name")
    val name: String?,
)