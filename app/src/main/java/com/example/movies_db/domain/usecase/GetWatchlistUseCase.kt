package com.example.movies_db.domain.usecase

import com.example.movies_db.domain.repository.MovieRepository
import javax.inject.Inject

// Example: Logic to get only movies saved by the user
class GetWatchlistUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    // This function can be called directly by the ViewModel
    // operator fun invoke() = repository.getAllMovies()
}