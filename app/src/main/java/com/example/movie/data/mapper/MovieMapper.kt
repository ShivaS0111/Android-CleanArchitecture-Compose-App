package com.example.movie.data.mapper

import com.example.movie.data.datasource.local.entities.MovieEntity
import com.example.movie.domain.model.Movie

fun Movie.toEntity() = MovieEntity(id, image, language, name)

fun MovieEntity.toDomain()=Movie(id, image, language, name)