package com.example.networksdk.data.datasource

import com.example.networksdk.data.model.MovieDetail
import com.example.networksdk.data.model.MovieList
import com.example.networksdk.data.utils.DataResult

interface MoviesDataSource {
    suspend fun fetchLatestMovies() : DataResult<MovieList>
    suspend fun fetchPopularMovies() : DataResult<MovieList>
    suspend fun fetchMovieDetail(movieId : String) : DataResult<MovieDetail>
}