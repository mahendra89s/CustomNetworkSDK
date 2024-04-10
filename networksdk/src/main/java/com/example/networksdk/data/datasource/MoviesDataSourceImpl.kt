package com.example.networksdk.data.datasource

import com.example.networksdk.data.ApiServices
import com.example.networksdk.data.model.MovieDetail
import com.example.networksdk.data.model.MovieList
import com.example.networksdk.data.utils.DataResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import javax.inject.Inject

class MoviesDataSourceImpl @Inject constructor(
    retrofit: Retrofit
) : MoviesDataSource {

    private val songsApiService by lazy { retrofit.create(ApiServices::class.java) }
    override suspend fun fetchLatestMovies(): DataResult<MovieList> {
        return withContext(Dispatchers.IO) {
            try {
                val response = songsApiService.fetchLatestMovies()
                if (response.isSuccessful) {
                    DataResult.Success(response.body()!!)
                } else {
                    DataResult.Error(response.message())
                }
            } catch (exception: Exception) {
                DataResult.Error(
                    exception.message ?: ""
                )
            }
        }
    }

    override suspend fun fetchPopularMovies(): DataResult<MovieList> {
        return withContext(Dispatchers.IO) {
            try {
                val response = songsApiService.fetchPopularMovies()
                if (response.isSuccessful) {
                    DataResult.Success(response.body()!!)
                } else {
                    DataResult.Error(response.message())
                }
            } catch (exception: Exception) {
                DataResult.Error(
                    exception.message ?: ""
                )
            }
        }
    }

    override suspend fun fetchMovieDetail(movieId: String): DataResult<MovieDetail> {
        return withContext(Dispatchers.IO) {
            try {
                val response = songsApiService.fetchMovieDetails()
                if (response.isSuccessful) {
                    DataResult.Success(response.body()!!)
                } else {
                    DataResult.Error(response.message())
                }
            } catch (exception: Exception) {
                DataResult.Error(
                    exception.message ?: ""
                )
            }
        }
    }
}