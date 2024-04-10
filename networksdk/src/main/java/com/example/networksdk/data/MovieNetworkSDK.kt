package com.example.networksdk.data

import com.example.networksdk.data.model.MovieDetail
import com.example.networksdk.data.model.MovieList
import com.example.networksdk.data.repository.MoviesRepository
import com.example.networksdk.data.utils.DataResult
import javax.inject.Inject

class MovieNetworkSDK @Inject constructor(
    private val moviesRepository: MoviesRepository
) {

    suspend fun getLatestMovies(): DataResult<MovieList> {
        return moviesRepository.fetchLatestMovies()
    }

    suspend fun getPopularMovies(): DataResult<MovieList> {
        return moviesRepository.fetchPopularMovies()
    }

    suspend fun getMovieDetail(movieId: String): DataResult<MovieDetail> {
        return moviesRepository.fetchMovieDetail(movieId)
    }

}