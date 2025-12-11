package com.example.movies_db.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val posterUrl: String,
    val releaseDate: String,
    val inWatchlist: Boolean = false
)