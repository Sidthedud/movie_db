package com.example.movies_db.domain.repository

import com.example.movies_db.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getPopularMovies(page: Int): List<Movie>
    fun getWatchlist(): Flow<List<Movie>>
    suspend fun toggleWatchlist(movie: Movie)
}