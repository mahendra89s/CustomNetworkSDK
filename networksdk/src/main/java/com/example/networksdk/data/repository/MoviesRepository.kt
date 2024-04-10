package com.example.networksdk.data.repository

import com.example.networksdk.data.model.MovieDetail
import com.example.networksdk.data.model.MovieList
import com.example.networksdk.data.utils.DataResult

interface MoviesRepository {
    suspend fun fetchLatestMovies() : DataResult<MovieList>
    suspend fun fetchPopularMovies() : DataResult<MovieList>
    suspend fun fetchMovieDetail(movieId : String) : DataResult<MovieDetail>
}