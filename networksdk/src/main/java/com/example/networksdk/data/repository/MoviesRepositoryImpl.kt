package com.example.networksdk.data.repository

import com.example.networksdk.data.datasource.MoviesDataSource
import com.example.networksdk.data.model.MovieDetail
import com.example.networksdk.data.model.MovieList
import com.example.networksdk.data.utils.DataResult
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val movieDataSource: MoviesDataSource
) : MoviesRepository {

    override suspend fun fetchLatestMovies(): DataResult<MovieList> {
        return movieDataSource.fetchLatestMovies()
    }

    override suspend fun fetchPopularMovies(): DataResult<MovieList> {
        return movieDataSource.fetchPopularMovies()
    }

    override suspend fun fetchMovieDetail(movieId : String): DataResult<MovieDetail> {
        return movieDataSource.fetchMovieDetail(movieId)
    }

}