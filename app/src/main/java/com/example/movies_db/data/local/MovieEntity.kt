package com.example.movies_db.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val posterUrl: String,
    val overview: String,
    val voteAverage: Double,
    val genreIds: String,
    val backdropPath: String?,
    val isUpcoming: Boolean,
    val releaseDate: String
)
