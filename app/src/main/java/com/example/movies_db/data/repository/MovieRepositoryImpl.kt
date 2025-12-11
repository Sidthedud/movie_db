package com.example.movies_db.data.repository

import com.example.movies_db.data.local.MovieDao
import com.example.movies_db.data.local.MovieEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import com.example.movies_db.data.remote.ApiService
import com.example.movies_db.domain.model.Movie
import com.example.movies_db.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val movieDao: MovieDao
) : MovieRepository {

    override suspend fun getPopularMovies(page: Int): List<Movie> {
        // TODO: Call apiService.getPopularMovies() and map to Domain Movie
        return emptyList()
    }

    override fun getWatchlist(): Flow<List<Movie>> {
        // TODO: Call movieDao.getAllMovies() and map to Domain Movie
        return kotlinx.coroutines.flow.emptyFlow()
    }

    override suspend fun toggleWatchlist(movie: Movie) {
        // TODO: Logic to add/remove from DB
    }
}