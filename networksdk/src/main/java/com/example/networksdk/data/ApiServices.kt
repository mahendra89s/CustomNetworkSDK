package com.example.networksdk.data

import com.example.networksdk.data.model.MovieDetail
import com.example.networksdk.data.model.MovieList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServices {
    @GET("movie/popular")
    suspend fun fetchPopularMovies(): Response<MovieList>

    @GET("discover/movie")
    suspend fun fetchLatestMovies(): Response<MovieList>

    @GET("movie/{movie_id}")
    suspend fun fetchMovieDetails(
        @Path("movie_id") movieId: String
    ): Response<MovieDetail>
}