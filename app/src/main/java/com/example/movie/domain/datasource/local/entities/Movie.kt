package com.example.movie.domain.datasource.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movie.domain.model.ImageData
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey
    val id: Int?,

    @SerializedName("Image")
    val image: ImageData?,

    @SerializedName("language")
    val language: String?,

    @SerializedName("name")
    val name: String?,
)